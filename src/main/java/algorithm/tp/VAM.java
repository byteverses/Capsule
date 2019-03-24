package algorithm.tp;

import data.Tuple;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Vogel Approximation Method (VAM)
 */
public class VAM {
    
    private double[] supplies;
    private double[] demands;
    
    private double[][] cost;
    
    private Set<Integer> leftSupplyIdxes;
    private Set<Integer> leftDemandIdxes;
    
    private Map<Tuple<Integer, Integer>, Double> results = new LinkedHashMap<>();
    
    public VAM(double[] supply, double[] demand, double[][] cost) {
        this.supplies = supply;
        this.demands = demand;
        this.cost = cost;
        
        leftSupplyIdxes = IntStream.range(0, supply.length)
                                   .boxed()
                                   .collect(Collectors.toCollection(LinkedHashSet::new));
        leftDemandIdxes = IntStream.range(0, demand.length)
                                   .boxed()
                                   .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    public void execute() {
        while (this.leftSupplyIdxes.size() > 1 && this.leftDemandIdxes.size() > 1) {
            this.iterativeStepAssign();
        }
        
        for (Integer leftSupplyIdx : this.leftSupplyIdxes) {
            for (Integer leftDemandIdx : this.leftDemandIdxes) {
                double assignQty = Math.min(this.supplies[leftSupplyIdx], this.demands[leftDemandIdx]);
                this.results.put(new Tuple<>(leftSupplyIdx, leftDemandIdx), assignQty);
            }
        }
    }
    
    private void iterativeStepAssign() {
        // 1. calculate (2nd min cost - 1st min cost) for each left rows and cols.
        Map<Integer, Double> rowMinDiff = this.rowMinDiff(this.cost, this.leftSupplyIdxes, this.leftDemandIdxes);
        Map<Integer, Double> colMinDiff = this.colMinDiff(this.cost, this.leftSupplyIdxes, this.leftDemandIdxes);
        
        // 2. get the max diff for all rows & cols.
        Optional<Map.Entry<Integer, Double>> maxRow = rowMinDiff.entrySet()
                                                                .stream()
                                                                .max(Comparator.comparing(Map.Entry::getValue));
        
        Optional<Map.Entry<Integer, Double>> maxCol = colMinDiff.entrySet()
                                                                .stream()
                                                                .max(Comparator.comparing(Map.Entry::getValue));
        
        if (maxRow.isPresent() && maxCol.isPresent()) {
            Map.Entry<Integer, Double> rowEntry = maxRow.get();
            Map.Entry<Integer, Double> colEntry = maxCol.get();
            
            // 3. find max row or col and assign qty for the lowest cost.
            int rowIdx;
            int colIdx;
            if (rowEntry.getValue() >= colEntry.getValue()) {
                rowIdx = rowEntry.getKey();
                Optional<Integer> colMin = this.leftDemandIdxes.stream()
                                                               .min(Comparator.comparing(idx -> this.cost[rowIdx][idx]));
                colIdx = colMin.isPresent() ? colMin.get() : -1;
            } else {
                colIdx = colEntry.getKey();
                Optional<Integer> rowMin = this.leftSupplyIdxes.stream()
                                                               .min(Comparator.comparing(idx -> this.cost[idx][colIdx]));
                rowIdx = rowMin.isPresent() ? rowMin.get() : -1;
            }
            
            double supply = this.supplies[rowIdx];
            double demand = this.demands[colIdx];
            if (supply > demand) {
                this.supplies[rowIdx] -= demand;
                this.results.put(new Tuple<>(rowIdx, colIdx), demand);
                this.leftDemandIdxes.remove(colIdx);
            } else {
                this.demands[colIdx] -= supply;
                this.results.put(new Tuple<>(rowIdx, colIdx), supply);
                this.leftSupplyIdxes.remove(rowIdx);
            }
        }
    }
    
    private Map<Integer, Double> rowMinDiff(double[][] cost, Set<Integer> rowIdxes, Set<Integer> colIdxes) {
        LinkedHashMap<Integer, Double> rowMinDiff = new LinkedHashMap<>();
        for (Integer rowIdx : rowIdxes) {
            double[] rowData = cost[rowIdx];
            double min1st = Double.MAX_VALUE;
            double min2nd = Double.MAX_VALUE;
            for (Integer colIdx : colIdxes) {
                double value = rowData[colIdx];
                if (value < min1st) {
                    min2nd = min1st;
                    min1st = value;
                } else if (value < min2nd) {
                    min2nd = value;
                }
            }
            rowMinDiff.put(rowIdx, min2nd - min1st);
        }
        
        return rowMinDiff;
    }
    
    private Map<Integer, Double> colMinDiff(double[][] cost, Set<Integer> rowIdxes, Set<Integer> colIdxes) {
        LinkedHashMap<Integer, Double> colMinDiff = new LinkedHashMap<>();
        for (Integer colIdx : colIdxes) {
            double min1st = Double.MAX_VALUE;
            double min2nd = Double.MAX_VALUE;
            for (Integer rowIdx : rowIdxes) {
                double value = cost[rowIdx][colIdx];
                if (value < min1st) {
                    min2nd = min1st;
                    min1st = value;
                } else if (value < min2nd) {
                    min2nd = value;
                }
            }
            colMinDiff.put(colIdx, min2nd - min1st);
        }
        return colMinDiff;
    }
    
    public Map<Tuple<Integer, Integer>, Double> getResults() {
        return this.results;
    }
}
