package gmail.Lance5057.network;

import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gmail.Lance5057.tileentities.TileEntity_CrestMount;

public class Handler_CrestMount implements IMessageHandler<Message_CrestMount, IMessage> {

    @Override
    public IMessage onMessage(Message_CrestMount message, MessageContext ctx) {
        TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
        if (te instanceof TileEntity_CrestMount) {
            ((TileEntity_CrestMount) te).flip = message.flip;
        }
        return null;
    }
}
