import java.util.ArrayList;

public class Genome {
    /* this represent the connection between the genes of this genome */
    private ArrayList<connectionGene> genesConnections = new  ArrayList<>();
    /* this represent the genes of this genome */
    ArrayList<Gene> genes = new ArrayList<>();
    /* the number of input genes in the network */
    private int inputs;
    /* the number of output genes in the network */
    private int outputs;
    /* the number of layer in the network */
    private int layers = 2;
    /* a special counter to store the id up to now in this genome */
    private int nextGeneId = 0;
    /* the special id of the bias input */
    private int biasNode;
    /* this represent the genes of this genome in order of layers (after sorting) */
    private ArrayList<Gene> network = new ArrayList<>();
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param inputs the number of input neurons
     * @param outputs the number of output neurons
     */
    Genome(int inputs, int outputs) {
        //set input number and output number
        this.inputs = inputs;
        this.outputs = outputs;

        // create input nodes
        for (int i = 0; i < inputs; i++) {
            genes.add(new Gene(i, 0)); //i = nextGeneId
            nextGeneId++;
        }

        // create output nodes
        for (int i = 0; i < outputs; i++) {
            genes.add(new Gene(i + inputs, 1)); //i + inputs = nextGeneId
            nextGeneId++;
        }

        //add bias node
        genes.add(new Gene(nextGeneId, 0));
        biasNode = nextGeneId;
        nextGeneId++;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * @param geneId the special number of this gene
     * @return the gene of this id
     */
    private Gene getGene(int geneId) {
        for (Gene gene: genes) {
            if (gene.getId() == geneId)
                return gene;
        }
        return null;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * adds the connections going out of a node to that node so that it can access the next node during
     * feeding forward
     */
    private void connectGenesToOutput() {
        //clear the connections of all the genes
        for (Gene gene : genes)
            gene.clearConnections();

        //for each connectionGene, add it to the input gene
        for (connectionGene connection : genesConnections)
            connection.getInputGene().addOutputConnection(connection);
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * sets up the network as a list of genes in order to be engaged to generate output
     */
    public void generateNetwork() {
        connectGenesToOutput();
        network = new ArrayList<>();
        //for each layer add the node in that layer,
        //since layers cannot connect to themselves
        //there is no need to order the nodes within a layer

        for (int l = 0; l < layers; l++) {//for each layer
            for (Gene gene : genes) //for each gene
                if(gene.getLayer() == l)
                    network.add(gene);
        }
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * this function feeds the inputs and returns the outputs of this genome
     */
    double[] feedForward(double[] inputValues) {
        //set the outputs of the input nodes (genes from 0 to inputs - 1)
        for (int i = 0; i < inputs; i++)
            genes.get(i).setOutputValue(inputValues[i]);
        //output of bias is 1
        genes.get(biasNode).setOutputValue(1);

        //for each node in the network engage it, feed forward
        for (Gene gene : network)
            gene.engageGene();

        //the outputs are gene[inputs] to gene [inputs + outputs - 1]
        double[] outputsNetwork = new double[outputs];
        for (int i = 0; i < outputs; i++)
            outputsNetwork[i] = genes.get(inputs + i).getOutput();

        //reset all the nodes for the next feed forward
        for (Gene gene : genes)
            gene.resetSum();

        return outputsNetwork;
    }
}
