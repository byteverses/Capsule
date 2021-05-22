package nlp;

import com.hankcs.hanlp.HanLP;

import java.util.List;

public class HanLPResearch {

    public static void main(String[] args) {

        String document = "今年恰逢《泰坦尼克号》上映20周年，而即将到来的11月11日也是男主“杰克”莱昂纳多的43周岁生日。这位每年要把 “光棍节” 过一遍的悲催男神，在电影中也...";
        List<String> keywords = HanLP.extractKeyword(document, 1);

        System.out.println("keywords = " + keywords);

        System.out.println("HanLP.convertToTraditionalChinese(document) = " + HanLP.convertToTraditionalChinese(document));

        String pinyinString = HanLP.convertToPinyinString(document, " ", false);
        System.out.println("pinyinString = " + pinyinString);

        System.out.println("HanLP.extractPhrase(document, 3) = " + HanLP.extractPhrase(document, 3));

        List<String> summary = HanLP.extractSummary(document, 3, ", ");
        System.out.println("summary = " + summary);

        System.out.println("HanLP.segment(document) = " + HanLP.segment(document));

    }
}
