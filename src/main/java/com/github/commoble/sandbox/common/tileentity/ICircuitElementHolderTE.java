package com.github.commoble.sandbox.common.tileentity;

import com.github.commoble.sandbox.common.electrical.CircuitElement;
import com.github.commoble.sandbox.common.electrical.Node;

/**
 * This interface implies that the implementing class holds a CircuitElement field 
 *
 */
public interface ICircuitElementHolderTE
{
	/** Intended usage: Create the circuit element, set its nodes to the given nodes, and apply it
	 * to the implementing class's CircuitElement field,
	 * and then return the CircuitElement
	 */
	public CircuitElement createCircuitElement(Node nodeA, Node nodeB);
}
