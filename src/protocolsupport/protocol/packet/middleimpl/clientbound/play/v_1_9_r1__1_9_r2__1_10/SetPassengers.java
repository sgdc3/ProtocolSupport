package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_1_9_r1__1_9_r2__1_10;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleSetPassengers;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class SetPassengers extends MiddleSetPassengers<RecyclableCollection<ClientBoundPacketData>>  {

	@Override
	public RecyclableCollection<ClientBoundPacketData> toData(ProtocolVersion version) throws IOException {
		ClientBoundPacketData serializer = ClientBoundPacketData.create(ClientBoundPacket.PLAY_SET_PASSENGERS, version);
		serializer.writeVarInt(vehicleId);
		serializer.writeVarInt(passengersIds.length);
		for (int passengerId : passengersIds) {
			serializer.writeVarInt(passengerId);
		}
		return RecyclableSingletonList.create(serializer);
	}

}