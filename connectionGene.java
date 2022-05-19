import java.util.Random;

public class connectionGene {
    /* the input gene to this connection */
    private Gene inputGene;
    /* the output gene to this connection */
    private Gene outputGene;
    /* the weight this connection */
    private double weight;
    /* is the connection enabled? */
    private boolean enabledConnection;
    /* the special number of this connection */
    private int innovationNumber;

    /* the random object of this connection, maybe change to static later */
    private Random random = new Random();
    //--------------------------------------------------------------------------------------------------------

    /**
     * @param inputGene the input gene to this connection
     * @param outputGene the output gene to this connection
     * @param weight the weight this connection
     * @param innovationNumber the special number of this connection
     */
    public connectionGene(Gene inputGene, Gene outputGene, double weight, int innovationNumber){
        this(inputGene, outputGene, weight, innovationNumber, true);
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @param inputGene the input gene to this connection
     * @param outputGene the output gene to this connection
     * @param weight the weight this connection
     * @param innovationNumber the special number of this connection
     * @param enabledConnection is the connection enabled?
     */
    private connectionGene(Gene inputGene, Gene outputGene, double weight, int innovationNumber,
                          boolean enabledConnection){
        this.inputGene = inputGene;
        this.outputGene = outputGene;
        this.weight = weight;
        this.innovationNumber = innovationNumber;
        this.enabledConnection = enabledConnection;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * changes the weight of this connection
     */
    void mutateWeightConnection() {
        double randomValue = Helper.getRandomBetween(random, 0, 1);
        //10% of the time completely change the weight
        if (randomValue < 0.1) {
            weight = Helper.getRandomBetween(random, -1, 1);
            return;
        }
        //otherwise slightly change the weight
        weight += random.nextGaussian() / 50;
        //keep weight between bounds
        weight = Helper.clamp(weight, -1, 1);
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @param inputGene the gene to be the input gene after the copy
     * @param outputGene the gene to be the output gene after the copy
     * @return returns a copy of this connectionGene
     */
    public connectionGene clone(Gene inputGene, Gene  outputGene) {
        return new connectionGene(inputGene, outputGene, weight, innovationNumber, enabledConnection);
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return get the input gene
     */
    public Gene getInputGene(){
        return this.inputGene;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return get the output gene
     */
    public Gene getOutputGene(){
        return this.outputGene;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return get the special number of this connection
     */
    public int getInnovationNumber(){
        return this.innovationNumber;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return is the connection enabled?
     */
    public boolean getEnabled(){
        return this.enabledConnection;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @return the weight of this connection
     */
    public double getWeight(){
        return this.weight;
    }
}
