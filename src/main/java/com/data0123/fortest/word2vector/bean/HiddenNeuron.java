package com.data0123.fortest.word2vector.bean;

public class HiddenNeuron extends Neuron{
    
    public double[] syn1 ; //hidden->out
    
    public HiddenNeuron(int layerSize){
        syn1 = new double[layerSize] ;
    }
    
}
