import java.util.*;

// Class representing molecular data
public class MolecularData {

    // Private fields
    private final List<Molecule> molecules; // List of molecules

    // Constructor
    public MolecularData(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    // Getter for molecules
    public List<Molecule> getMolecules() {
        return molecules;
    }

    // Method to identify molecular structures
    // Return the list of different molecular structures identified from the input data

    public List<MolecularStructure> identifyMolecularStructures() {
        ArrayList<MolecularStructure> structures = new ArrayList<>();
        ArrayList<String> moleculeButJustName = new ArrayList<>();




        for(Molecule molecule : molecules)
        {
            moleculeButJustName.add(molecule.getId());
        }

        int lastStructerIndex = 0;



        for(Molecule molecule : molecules)
        {
            List<Molecule> bonds = new ArrayList<>();
            for(int i = 0; i < molecule.getBonds().size(); i++)
            {

                for (Molecule value : molecules) {
                    if (molecule.getBonds().get(i).equals(value.getId())) {
                        bonds.add(value);
                    }
                }
            }

            boolean check = false;
            int howManyBond = 0;
            for(Molecule bondMolecule : bonds)
            {
                int i = 0;
                while(i < structures.size())
                {
                    if(structures.get(i).getMolecules().contains(molecule))
                    {
                        check = true;
                        break;
                    }
                    else if(structures.get(i).getMolecules().contains(bondMolecule))
                    {
                        structures.get(i).getMolecules().add(molecule);
                        check = true;
                        for (Molecule bond : bonds) {
                            if (bond.getBonds().size() == 0 && !structures.get(i).getMolecules().contains(bond)) {
                                structures.get(i).getMolecules().add(bond);
                            }
                        }
                        break;
                    }
                    i++;
                }
                if(check)
                    break;
                howManyBond++;
            }
            if(howManyBond == bonds.size() && bonds.size() != 0)         // yerleşmiş bond bulunamadı
            {
                structures.add(new MolecularStructure());
                structures.get(lastStructerIndex).addMolecule(molecule);
                for (Molecule bond : bonds) {
                    structures.get(lastStructerIndex).addMolecule(bond);
                }
                lastStructerIndex++;
            }

        }

        for(int i = 1 ; i < structures.size(); i++)
        {
            for(int j = 0; j < i; j++)
            {
                for(int k = 0; k < structures.get(i).getMolecules().size(); k++)
                {
                    if(structures.get(j).getMolecules().contains(structures.get(i).getMolecules().get(k)))
                    {
                        for(int z = 0;z < structures.get(i).getMolecules().size(); z++)
                        {
                            if(!structures.get(j).getMolecules().contains(structures.get(i).getMolecules().get(z)))
                                structures.get(j).getMolecules().add(structures.get(i).getMolecules().get(z));
                        }
                        structures.remove(i);
                        break;
                    }
                }
            }
        }



        return structures;
    }

    // Method to print given molecular structures
    public void printMolecularStructures(List<MolecularStructure> molecularStructures, String species) {

        /* YOUR CODE HERE */
        System.out.println(molecularStructures.size() + " molecular structures have been discovered " + species);
        for(int i = 0; i < molecularStructures.size(); i++)
        {
            System.out.println("Molecules in Molecular Structure "+ (i+1) + ": " + molecularStructures.get(i));
        }



    }

    // Method to identify anomalies given a source and target molecular structure
    // Returns a list of molecular structures unique to the targetStructure only
    public static ArrayList<MolecularStructure> getVitalesAnomaly(List<MolecularStructure> sourceStructures, List<MolecularStructure> targeStructures) {
        ArrayList<MolecularStructure> anomalyList = new ArrayList<>();

        /* YOUR CODE HERE */

        for (MolecularStructure targeStructure : targeStructures) {
            boolean bool = true;
            for (MolecularStructure sourceStructure : sourceStructures) {
                if (sourceStructure.equals(targeStructure)) {
                    bool = false;
                }
            }
            if (bool) {
                anomalyList.add(targeStructure);
            }
        }

        return anomalyList;
    }

    // Method to print Vitales anomalies
    public void printVitalesAnomaly(List<MolecularStructure> molecularStructures) {

        /* YOUR CODE HERE */
        System.out.println("Molecular structures unique to Vitales individuals:");
        for (MolecularStructure molecularStructure : molecularStructures) {
            System.out.println(molecularStructure);
        }
    }
}
