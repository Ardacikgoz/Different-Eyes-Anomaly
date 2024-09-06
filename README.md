### Genesis Project

This project simulates the **Genesis Mission** and the **Synthesis Mission** aimed at analyzing and synthesizing molecular structures between typical humans and a fictional species called **Vitales**.

#### Key Components:

- **Mission Genesis**: 
  - Reads molecular data from an XML file for both humans and Vitales.
  - Identifies molecular structures and detects anomalies between the two species.
  
- **Mission Synthesis**:
  - Synthesizes bonds between molecular structures to create a serum.
  - Prints detailed output on the molecules and bond strengths involved in the serum formation.

#### Main Classes:
- `Bond`: Represents the connection between two molecules.
- `MolecularData`: Handles molecular information and structure identification.
- `Molecule`: Represents an individual molecule, including its bond strength and connections.
- `MolecularStructure`: Represents a group of molecules connected by bonds.
- `MissionGenesis`: Reads and processes molecular data from an XML file.
- `MissionSynthesis`: Synthesizes bonds between molecular structures to form the serum.

#### How to Run:
The program takes an XML file as input containing molecular data for humans and Vitales. The program then identifies molecular structures, synthesizes bonds, and prints the results of the synthesis process.

Example usage:
  java Main molecularData.xml
