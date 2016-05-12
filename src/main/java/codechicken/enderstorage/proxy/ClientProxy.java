package codechicken.enderstorage.proxy;

import codechicken.core.CCUpdateChecker;
import codechicken.enderstorage.client.EnderPouchModelLoader;
import codechicken.enderstorage.client.render.RenderTileEnderChest;
import codechicken.enderstorage.client.render.RenderTileEnderTank;
import codechicken.enderstorage.handler.ConfigurationHandler;
import codechicken.enderstorage.init.ModBlocks;
import codechicken.enderstorage.init.ModItems;
import codechicken.enderstorage.network.EnderStorageCPH;
import codechicken.enderstorage.tile.TileEnderChest;
import codechicken.enderstorage.tile.TileEnderTank;
import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by covers1624 on 4/11/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        ModelLoaderRegistry.registerLoader(new EnderPouchModelLoader());
        ModBlocks.registerModels();
        ModItems.registerModels();
    }

    @Override
    public void init() {
        if (ConfigurationHandler.clientCheckUpdates) {
            CCUpdateChecker.updateCheck("EnderStorage");
        }
        super.init();
        PacketCustom.assignHandler(EnderStorageCPH.channel, new EnderStorageCPH());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnderChest.class, new RenderTileEnderChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnderTank.class, new RenderTileEnderTank());
    }
}
