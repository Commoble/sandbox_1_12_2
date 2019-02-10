package com.github.commoble.sandbox.common.tileentity;

import com.github.commoble.sandbox.common.electrical.CircuitElement;
import com.github.commoble.sandbox.common.electrical.Node;
import com.github.commoble.sandbox.common.electrical.ResistorElement;

import net.minecraft.tileentity.TileEntity;

public class TileEntityLightbulb extends TileEntity implements ICircuitElementHolderTE
{
	public ResistorElement element;

	@Override
	public CircuitElement createCircuitElement(Node nodeA, Node nodeB)
	{
		this.element = new ResistorElement(this.world, this.pos, nodeA, nodeB, 1000D);
		return this.element;
	}

}
