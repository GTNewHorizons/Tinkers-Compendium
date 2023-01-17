package lance5057.tDefense.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class Message_FinishingAnvil implements IMessage {
    ByteBufUtils bbu = new ByteBufUtils();

    public int x, y, z;
    public ItemStack item;

    public Message_FinishingAnvil() {}

    public Message_FinishingAnvil(int x, int y, int z, ItemStack item) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.item = item;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        item = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        ByteBufUtils.writeItemStack(buf, item);
    }
}
