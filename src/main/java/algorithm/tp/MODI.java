package algorithm.tp;

import data.KV;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * MODI(modified distribution) Method
 */
public class MODI {

    private double[] supply;
    private double[] demand;
    private double[][] cost;

    private Map<KV<Integer, Integer>, Double> optimizedResults;

    public MODI(double[] supply, double[] demand, double[][] cost, Map<KV<Integer, Integer>, Double> initialResults) {
        this.supply = supply;
        this.demand = demand;
        this.cost = cost;
        this.optimizedResults = new LinkedHashMap<>(initialResults);
    }

    public Map<KV<Integer, Integer>, Double> optimize() {

        List<KV<Integer, Integer>> negativePositions = findNegativePositions();

        while (!negativePositions.isEmpty()) {
            for (KV<Integer, Integer> negativePosition : negativePositions) {
                if (this.optimizeNegativePosition(negativePosition)) {
                    break;
                }
            }
            negativePositions = findNegativePositions();
        }

        return this.optimizedResults;
    }

    private List<KV<Integer, Integer>> findNegativePositions() {
        Set<Integer> leftSupplyIdxes = new HashSet<>();
        Set<Integer> leftDemandIdxes = new HashSet<>();

        double[] row = new double[supply.length];
        double[] col = new double[demand.length];

        // there are m + n - 1 (supply count + demand count - 1) initial result.
        // Here for each assign, build Si + Dj = cost[i][j] (i in range(0,m), j in range(0, n))
        // So: there are m + n unknown number and m + n - 1 equation
        // 1. to solve this equation, let's set s0 = 0
        row[0] = 0.0;
        leftSupplyIdxes.add(0);

        // 2. solve this equation.
        while (leftSupplyIdxes.size() < supply.length || leftDemandIdxes.size() < demand.length) {
            this.optimizedResults.forEach((key, value) -> {
                Integer supply = key.getKey();
                Integer demand = key.getValue();
                if (!leftSupplyIdxes.contains(supply) && leftDemandIdxes.contains(demand)) {
                    row[supply] = this.cost[supply][demand] - col[demand];
                    leftSupplyIdxes.add(supply);
                }
                if (leftSupplyIdxes.contains(supply) && !leftDemandIdxes.contains(demand)) {
                    col[demand] = this.cost[supply][demand] - row[supply];
                    leftDemandIdxes.add(demand);
                }
            });
        }

        // 3. calculate negative costs position.
        Map<KV<Integer, Integer>, Double> negativePositions = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < col.length; j++) {
                double improveCosts = this.cost[i][j] - row[i] - col[j];
                if (improveCosts < 0) {
                    // negative position
                    negativePositions.put(new KV<>(i, j), improveCosts);
                }
            }
        }

        return negativePositions.entrySet()
                                .stream()
                                .sorted(Comparator.comparing(Map.Entry::getValue))
                                .map(entry -> entry.getKey())
                                .collect(Collectors.toList());
    }

    private boolean optimizeNegativePosition(KV<Integer, Integer> negativePosition) {

        Set<KV<Integer, Integer>> assignPositions = this.optimizedResults.entrySet()
                                                                         .stream()
                                                                         .map(Map.Entry::getKey)
                                                                         .collect(Collectors.toSet());
        // add current negative position
        assignPositions.add(negativePosition);

        Map<Integer, Set<KV<Integer, Integer>>> rowPositions = assignPositions.stream()
                                                                              .collect(Collectors.groupingBy(KV::getKey,
                                                                                                             Collectors.toSet()));
        Map<Integer, Set<KV<Integer, Integer>>> colPositions = assignPositions.stream()
                                                                              .collect(Collectors.groupingBy(KV::getValue,
                                                                                                             Collectors.toSet()));

        Stack<KV<Integer, Integer>> closedPath = this.findClosedLoopPath(negativePosition, rowPositions, colPositions);

        if (!closedPath.isEmpty()) {

            KV<Integer, Integer> minPosition = IntStream.range(1, closedPath.size())
                                                        .filter(i -> i % 2 == 1)
                                                        .mapToObj(closedPath::get)
                                                        .min(Comparator.comparing(this.optimizedResults::get))
                                                        .get();
            double minAssign = this.optimizedResults.get(minPosition);
            this.optimizedResults.put(negativePosition, minAssign);
            IntStream.range(1, closedPath.size())
                     .forEach(index -> this.optimizedResults.merge(closedPath.get(index),
                                                                   index % 2 == 1 ? -minAssign : minAssign,
                                                                   Double::sum));

            this.optimizedResults.remove(minPosition);

            return true;

        }

        return false;
    }

    private Stack<KV<Integer, Integer>> findClosedLoopPath(KV<Integer, Integer> startEndPosition,
                                                           Map<Integer, Set<KV<Integer, Integer>>> rowPositions,
                                                           Map<Integer, Set<KV<Integer, Integer>>> colPositions) {

        Stack<KV<Integer, Integer>> closedPath = new Stack<>();

        closedPath.push(startEndPosition);

        Stack<KV<Integer, Integer>> pathPositions = this.searchNextPosition(startEndPosition,
                                                                            closedPath,
                                                                            true,
                                                                            rowPositions,
                                                                            colPositions);
        if (pathPositions.isEmpty()) {
            pathPositions = this.searchNextPosition(startEndPosition,
                                                    closedPath,
                                                    false,
                                                    rowPositions,
                                                    colPositions);
        }

        return pathPositions;
    }

    private Stack<KV<Integer, Integer>> searchNextPosition(KV<Integer, Integer> startEndPosition,
                                                           Stack<KV<Integer, Integer>> closedPath,
                                                           boolean isHorizontallySearch,
                                                           Map<Integer, Set<KV<Integer, Integer>>> rowPositions,
                                                           Map<Integer, Set<KV<Integer, Integer>>> colPositions) {

        KV<Integer, Integer> currPosition = closedPath.peek();
        Set<KV<Integer, Integer>> nextPositions = isHorizontallySearch
                ? rowPositions.get(currPosition.getKey())
                : colPositions.get(currPosition.getValue());

        for (KV<Integer, Integer> nextPosition : nextPositions) {
            if (nextPosition.equals(startEndPosition) && !nextPosition.equals(currPosition)) {
                return closedPath;
            } else if (!closedPath.contains(nextPosition)) {
                closedPath.push(nextPosition);
                Stack<KV<Integer, Integer>> searchPosition = this.searchNextPosition(startEndPosition,
                                                                                     closedPath,
                                                                                     !isHorizontallySearch,
                                                                                     rowPositions,
                                                                                     colPositions);
                if (searchPosition.isEmpty()) {
                    closedPath.pop();
                } else {
                    return closedPath;
                }
            }
        }
        return new Stack<>();
    }
}
