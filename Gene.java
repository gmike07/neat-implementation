import java.util.ArrayList;

public class Gene {
    /* the special id of this gene */
    private int id;
    /* holds the sum before activation function is applied */
    private double inputSum = 0;
    /* holds the sum after activation function is applied */
    private double outputValue = 0;
    /* holds the output connections of this gene (i.e. to who to output) */
    private ArrayList<connectionGene> outputConnections = new ArrayList<>();
    /* the current layer of this gene in the network */
    private int geneLayer = 0;
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param number the special number of this gene
     */
    public Gene(int number) {
        this.id = number;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @param number the special number of this gene
     * @param layer the current layer of this gene in the network
     */
    public Gene(int number, int layer) {
        this.id = number;
        this.geneLayer = layer;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     *  returns a copy of this gene
     */
    public Gene clone() {
        return new Gene(id, geneLayer);
    }


    //--------------------------------------------------------------------------------------------------------
    /**
     * @param gene a gene to check
     * @return true if the given gene is output gene of this gene, else false
     */
    private boolean isGeneInputOf(Gene gene) {
        for (connectionGene connection : outputConnections){
            if (connection.getOutputGene() == gene)
                return true;
        }
        return false;
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * this function is used when adding a new connection
     * @param gene a gene to check whether it is connected to this one
     * @return  whether this gene is connected to the parameter gene
     */
    public boolean isConnectedTo(Gene gene) {
        if (gene.getLayer() == this.getLayer()) //genes in the same layer cannot be connected
            return false;

        if (gene.getLayer() < this.getLayer())  //if the gene should be the input
            return gene.isGeneInputOf(this);
        else //this is the input and gene should be the output
            return this.isGeneInputOf(gene);
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * the gene sends its output to the inputs of the genes its connected to
     */
    public void engageGene() {
        if (this.getLayer() != 0) {
            //no sigmoid for the inputs and bias
            outputValue = Helper.sigmoid(inputSum);
        }
        for(connectionGene outputConnection: outputConnections)
            if(outputConnection.getEnabled())
                outputConnection.getOutputGene().updateInputSum(outputValue * outputConnection.getWeight());
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * updates the input sum with the sum
     */
    private void updateInputSum(double sum) {
        this.inputSum += sum;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * reset the sum of this gene
     */
    public void resetSum() {
        this.inputSum = 0;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return the current layer of this gene
     */
    public int getLayer(){
        return this.geneLayer;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * this function clears the output genes that this gene is connected to
     */
    public void clearConnections(){
        this.outputConnections.clear();
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * @return the id of this gene
     */
    public int getId(){
        return this.id;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * add a connection to this gene
     */
    public void addOutputConnection(connectionGene connection){
        this.outputConnections.add(connection);
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * @return the value of this gene
     */
    public double getOutput(){
        return this.outputValue;
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param output the value to set
     * this function sets the output of this gene to be the output
     */
    public void setOutputValue(double output){
        this.outputValue = output;
    }
}
