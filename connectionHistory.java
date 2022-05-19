import java.util.ArrayList;

public class connectionHistory {
    private int inputGeneId;
    private int outputGeneId;
    private int innovationNumber;
    private ArrayList<Integer> innovationNumbers;//the innovation Numbers from the connections of
    // the
    // genome which first had this mutation
    //this represents the genome and allows us to test if another genome is the same
    //this is before this connection was added
    //--------------------------------------------------------------------------------------------------------
    //constructor
    connectionHistory(int inputGeneId, int outputGeneId, int innovationNumber,
                      ArrayList<Integer> innovationNumbers) {
        this.inputGeneId = inputGeneId;
        this.outputGeneId = outputGeneId;
        this.innovationNumber = innovationNumber;
        this.innovationNumbers = (ArrayList<Integer>)innovationNumbers.clone();
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param genome to compare to this connection history
     * @param input the input node of this connection
     * @param output the output node of this connection
     * @return whether the genome matches the original genome and the connection is between the same nodes
     */
    boolean matches(Genome genome, Gene input, Gene output) {
        //if the number of connections are different then the genomes aren't the same
        if (genome.genes.size() != innovationNumbers.size())
            return false;
        //if the input or output doesn't match then it is not the same connection
        if (input.getId() != inputGeneId || output.getId() != outputGeneId)
            return false;
        //next check if all the innovation numbers match from the genome
        for (Gene gene : genome.genes) {
            if (!innovationNumbers.contains(gene.getId()))
                return false;
        }
        //if reached this far then the innovationNumbers match the genes innovation numbers and the
        //connection is between the same nodes so it does match
        return true;
    }
}
