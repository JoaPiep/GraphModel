/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

/**
 *
 * @author Joanna Pieper <joanna.pieper@gcsc.uni-frankfurt.de>
 */
class VariableNode extends Node {

    private Object value;

    /**
     *
     * @param name variable's name
     */
    public VariableNode(String name) {
        this.name = name;
    }

    /**
     *
     * @param name variable's name
     * @param value value
     */
    public VariableNode(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return variable's value
     */
    public Object getValue() {
        return value;
    }

    /**
     *
     * @param value value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VariableNode{" + "value=" + value + '}';
    }
}
