import java.util.*;

// Class representing the Mission Synthesis
public class MissionSynthesis {

    // Private fields
    private final List<MolecularStructure> humanStructures; // Molecular structures for humans
    private final ArrayList<MolecularStructure> diffStructures; // Anomalies in Vitales structures compared to humans

    private ArrayList<Molecule> chosenHumanMolecules = new ArrayList<>();

    private ArrayList<Molecule> chosenDiffMolecules = new ArrayList<>();

    // Constructor
    public MissionSynthesis(List<MolecularStructure> humanStructures, ArrayList<MolecularStructure> diffStructures) {
        this.humanStructures = humanStructures;
        this.diffStructures = diffStructures;
    }

    // Method to synthesize bonds for the serum
    public List<Bond> synthesizeSerum() {
        List<Bond> serum = new ArrayList<>();
        List<Bond> checkAll = new ArrayList<>();
        List<Molecule> chosenMolecules = new ArrayList<>();
        List<Molecule> visitedMolecules = new ArrayList<>();

        for(MolecularStructure molecularStructure : humanStructures)
        {
            chosenMolecules.add(molecularStructure.getMoleculeWithWeakestBondStrength());
            chosenHumanMolecules.add(molecularStructure.getMoleculeWithWeakestBondStrength());
        }

        for(MolecularStructure molecularStructure: diffStructures)
        {
            chosenMolecules.add(molecularStructure.getMoleculeWithWeakestBondStrength());
            chosenDiffMolecules.add(molecularStructure.getMoleculeWithWeakestBondStrength());
        }

        for(int i = 0; i < chosenMolecules.size(); i++)
        {
            for(int j =i+1 ; j < chosenMolecules.size(); j++)
            {
                double weight = (double) (chosenMolecules.get(i).getBondStrength() + chosenMolecules.get(j).getBondStrength()) /2;
                Bond newBond = new Bond(chosenMolecules.get(i),chosenMolecules.get(j),weight);
                checkAll.add(newBond);
            }
        }


        int moleculeCount = chosenMolecules.size();
        int i = 0;
        while(i < moleculeCount - 1)
        {
            double minBound = checkAll.get(0).getWeight();
            Bond dummy = checkAll.get(0);
            for(int j = 0; j < checkAll.size(); j++)
            {
                if(minBound > checkAll.get(j).getWeight())
                {
                    dummy = checkAll.get(j);
                    minBound = checkAll.get(j).getWeight();
                }
            }
            if(visitedMolecules.contains(dummy.getFrom()) && visitedMolecules.contains(dummy.getTo()))
            {
                checkAll.remove(dummy);
            }
            else {
                serum.add(dummy);
                visitedMolecules.add(dummy.getFrom());
                visitedMolecules.add(dummy.getTo());
                checkAll.remove(dummy);
                i++;
            }
        }

        /* YOUR CODE HERE */

        return serum;
    }



    // Method to print the synthesized bonds
    public void printSynthesis(List<Bond> serum) {

        /* YOUR CODE HERE */

        System.out.println("Typical human molecules selected for synthesis: " + chosenHumanMolecules);
        System.out.println("Vitales molecules selected for synthesis: " + chosenDiffMolecules);
        System.out.println("Synthesizing the serum...");
        double total = 0;
        for(Bond bond : serum)
        {
            String formatliSayi = String.format("%.2f", bond.getWeight());
            System.out.println("Forming a bond between " + bond.getFrom() + " - " + bond.getTo() + " with strength "+
                    formatliSayi);
            total += bond.getWeight();
        }

        String format = String.format("%.2f",total);
        System.out.println("The total serum bond strength is " + format);
    }
}
