import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Class representing the mission of Genesis
public class MissionGenesis {

    // Private fields
    private MolecularData molecularDataHuman; // Molecular data for humans
    private MolecularData molecularDataVitales; // Molecular data for Vitales

    // Getter for human molecular data
    public MolecularData getMolecularDataHuman() {
        return molecularDataHuman;
    }

    // Getter for Vitales molecular data
    public MolecularData getMolecularDataVitales() {
        return molecularDataVitales;
    }

    // Method to read XML data from the specified filename
    // This method should populate molecularDataHuman and molecularDataVitales fields once called
    public void readXML(String filename) {
        /* YOUR CODE HERE */
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            molecularDataHuman = fonc("HumanMolecularData",doc);
            molecularDataVitales = fonc("VitalesMolecularData",doc);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MolecularData fonc(String nameOfMolecularData, Document doc)
    {
        NodeList list = doc.getElementsByTagName(nameOfMolecularData);
        List<Molecule> molecules = new ArrayList<>();
        if (list.getLength() > 0) {
            Node node = list.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element ElementX = (Element) node;
                NodeList moleculeList = ElementX.getElementsByTagName("Molecule");
                for(int i = 0; i < moleculeList.getLength(); i++)
                {
                    Node moleculeNode = moleculeList.item(i);
                    List<String> bondListOfMolecule = new ArrayList<>();
                    if(moleculeNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element moleculeElement = (Element) moleculeNode;

                        String moleculeID = moleculeElement.getElementsByTagName("ID").item(0).getTextContent();

                        String bondStrengthStr = moleculeElement.getElementsByTagName("BondStrength").item(0).getTextContent();

                        int bondStrength = Integer.parseInt(bondStrengthStr);

                        NodeList bondList = moleculeElement.getElementsByTagName("Bonds");


                        Node bondNode = bondList.item(0);
                        if(bondNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            String bondId = bondNode.getTextContent();
                            String[] arr = bondId.split("\n");
                            for(int j = 0 ; j < arr.length; j++)
                            {
                                String clearBond = arr[j].trim();
                                if(!clearBond.equals(""))
                                {
                                    bondListOfMolecule.add(clearBond);
                                }
                            }
                        }

                        Molecule molecule = new Molecule(moleculeID, bondStrength, bondListOfMolecule);
                        molecules.add(molecule);
                    }

                }
            }
        }
        return new MolecularData(molecules);
    }
}
