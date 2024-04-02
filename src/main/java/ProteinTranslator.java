import java.util.ArrayList;
import java.util.List;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        List<String> proteins = new ArrayList<>();
        for (int i = 0; i < rnaSequence.length(); i += 3) {
            if (i + 3 > rnaSequence.length()) throw new IllegalArgumentException("Invalid codon");

            var protein = translateSingleRNA(rnaSequence.substring(i, i + 3));
            if (protein.equals("STOP")) break;
            proteins.add(protein);
        }
        return proteins;
    }

    private String translateSingleRNA(String rna) {
        return switch(rna) {
            case "AUG" -> "Methionine";
            case "UUU", "UUC" -> "Phenylalanine";
            case "UUA", "UUG" -> "Leucine";
            case "UCU", "UCC", "UCA", "UCG" -> "Serine";
            case "UAU", "UAC" -> "Tyrosine";
            case "UGU", "UGC" -> "Cysteine";
            case "UGG" -> "Tryptophan";
            case "UAA", "UAG", "UGA" -> "STOP";
            default -> throw new IllegalArgumentException("Invalid codon");
        };
    }
}
