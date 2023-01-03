package com.opentext.qfiniti.importer.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CallRecordingTest {

	private CallRecording call;

	@BeforeEach
	public void initialize() {
		call = new CallRecording();
	}

	@Test
	public void testSetDefaultTeamMemberName() {
		call.setDefaultTeamMemberName();
		assertEquals("Esposito Esposito, Juan", call.getTeamMemberName());
	}

	@Test
	public void testSetTeamMemberName() {
		CallRecording call = new CallRecording();

		call.setTeamMemberName("Arturo Perez Reverte");
		assertEquals("Perez Reverte, Arturo", call.getTeamMemberName());

		call.setTeamMemberName("Raul Portales");
		assertEquals("Portales, Raul", call.getTeamMemberName());

		call.setTeamMemberName("Jorge Jesus Alejo Lopez");
		assertEquals("Alejo Lopez, Jorge Jesus", call.getTeamMemberName());

		call.setTeamMemberName("Perez Reverte, Arturo");
		assertEquals("Perez Reverte, Arturo", call.getTeamMemberName());
	}

}
