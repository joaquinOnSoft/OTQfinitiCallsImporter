/*
 *   (C) Copyright 2019 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.opentext.qfiniti.importer.io.ExcelReaderTest;
import com.opentext.qfiniti.importer.io.ExcelWriterTest;
import com.opentext.qfiniti.importer.io.filler.AniFillerTest;
import com.opentext.qfiniti.importer.io.filler.DnisFillerTest;
import com.opentext.qfiniti.importer.io.filler.DurationFromMetadataFillerTest;
import com.opentext.qfiniti.importer.io.filler.GroupHierarchyFillerTest;
import com.opentext.qfiniti.importer.io.filter.FolderFilterTest;
import com.opentext.qfiniti.importer.io.filter.WavFilterTest;
import com.opentext.qfiniti.importer.io.filter.XlsFilterTest;
import com.opentext.qfiniti.importer.io.metadata.JaudiotaggerMetadataExtractorTest;
import com.opentext.qfiniti.importer.io.metadata.TikaMetadataExtractorTest;
import com.opentext.qfiniti.importer.io.transformer.DateMMddyyyyhhmmssaTransformerTest;
import com.opentext.qfiniti.importer.io.transformer.HoursMinutesSecondsToSecondsTransformerTest;
import com.opentext.qfiniti.importer.io.transformer.InteractionIdKey2FileNameTransformerTest;
import com.opentext.qfiniti.importer.io.transformer.LocalPartyName2TeamMemberNameTransformerTest;
import com.opentext.qfiniti.importer.pojo.CallRecordingTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	//com.opentext.qfiniti
	JSonConfigReaderTest.class,
	XlsQfinitiICGTest.class,

	//com.opentext.qfiniti.importer.io.filler
	AniFillerTest.class,
	DnisFillerTest.class,
	DurationFromMetadataFillerTest.class,
	GroupHierarchyFillerTest.class,

	//com.opentext.qfiniti.importer.io.
	ExcelReaderTest.class,
	ExcelWriterTest.class,	
	
	//com.opentext.qfiniti.importer.io.filter
	FolderFilterTest.class,
	WavFilterTest.class,
	XlsFilterTest.class,

	//com.opentext.qfiniti.importer.io.metadata
	JaudiotaggerMetadataExtractorTest.class,
	TikaMetadataExtractorTest.class,

	//com.opentext.qfiniti.importer.io.transformer
	DateMMddyyyyhhmmssaTransformerTest.class,
	HoursMinutesSecondsToSecondsTransformerTest.class,
	InteractionIdKey2FileNameTransformerTest.class,
	LocalPartyName2TeamMemberNameTransformerTest.class,
	
	//com.opentext.qfiniti.importer.io.pojo
	CallRecordingTest.class	
})

public class QfinitiTestSuite {

}
