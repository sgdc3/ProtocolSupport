package protocolsupport.protocol.packet.middle.serverbound.play;

import org.bukkit.util.Vector;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.ServerBoundPacket;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.ServerBoundPacketData;
import protocolsupport.protocol.serializer.MiscSerializer;
import protocolsupport.protocol.serializer.VarNumberSerializer;
import protocolsupport.protocol.utils.EnumConstantLookups;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleUseEntity extends ServerBoundMiddlePacket {

	public MiddleUseEntity(ConnectionImpl connection) {
		super(connection);
	}

	protected int entityId;
	protected Action action;
	protected Vector interactedAt;
	protected int usedHand;

	@Override
	public RecyclableCollection<ServerBoundPacketData> toNative() {
		ServerBoundPacketData creator = ServerBoundPacketData.create(ServerBoundPacket.PLAY_USE_ENTITY);
		VarNumberSerializer.writeVarInt(creator, entityId);
		MiscSerializer.writeVarIntEnum(creator, action);
		switch (action) {
			case INTERACT: {
				VarNumberSerializer.writeVarInt(creator, usedHand);
				break;
			}
			case INTERACT_AT: {
				creator.writeFloat((float) interactedAt.getX());
				creator.writeFloat((float) interactedAt.getY());
				creator.writeFloat((float) interactedAt.getZ());
				VarNumberSerializer.writeVarInt(creator, usedHand);
				break;
			}
			case ATTACK: {
				break;
			}
		}
		return RecyclableSingletonList.create(creator);
	}

	protected enum Action {
		INTERACT, ATTACK, INTERACT_AT;
		public static final EnumConstantLookups.EnumConstantLookup<Action> CONSTANT_LOOKUP = new EnumConstantLookups.EnumConstantLookup<>(Action.class);
	}

}
