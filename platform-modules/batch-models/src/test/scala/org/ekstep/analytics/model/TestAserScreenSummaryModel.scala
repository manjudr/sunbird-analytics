package org.ekstep.analytics.model

import java.io.FileWriter

import org.ekstep.analytics.framework.JobContext
import org.ekstep.analytics.framework.util.CommonUtil
import org.ekstep.analytics.framework.DataFilter
import org.ekstep.analytics.framework.Filter
import org.ekstep.analytics.framework.util.JSONUtils
import org.ekstep.analytics.framework.MeasuredEvent
import org.ekstep.analytics.framework.MEEdata
import org.ekstep.analytics.framework.MeasuredEvent

import scala.collection.immutable.HashMap.HashTrieMap
import org.ekstep.analytics.framework.Event
import org.scalatest.Ignore

@Ignore @deprecated
class TestAserScreenSummaryModel extends SparkSpec(null) {

    //-----test1-------
    "AserScreenSummaryModel" should "check the correctness of summary events from 'raw.telemetry.test1.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test1.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(2)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("assessNumeracyQ1") should be(Option(0d))
        sec.get("assessNumeracyQ2") should be(Option(0d))
        sec.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("selectNumeracyQ2") should be(Option(0d))
        sec.get("scorecard") should be(Option(0d))
        sec.get("summary") should be(Option(0d))
    }
    //------- test2--------
    it should "check the correctness of summary events from 'raw.telemetry.test2.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test2.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(2)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("assessNumeracyQ3") should be(Option(0d))
    }
    //------- test3--------
    it should "check the correctness of summary events from 'raw.telemetry.test3.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test3.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
    }
    //------- test4--------
    it should "check the correctness of summary events from 'raw.telemetry.test4.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test4.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
    }
    //------- test5--------
    it should "check the correctness of summary events from 'raw.telemetry.test5.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test5.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(2)

        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("assessNumeracyQ3") should be(Option(0d))

        sec.get("selectNumeracyQ2") should be(Option(0d))
        sec.get("assessNumeracyQ2") should be(Option(0d))
        sec.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("scorecard") should be(Option(0d))
        sec.get("summary") should be(Option(0d))
    }
    //------- test6--------
    it should "check the correctness of summary events from 'raw.telemetry.test6.json'" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/raw.telemetry.test6.json");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(4)

        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val fourth = me(3).edata.eks.asInstanceOf[HashTrieMap[String, Double]];

        first.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("assessNumeracyQ3") should be(Option(0d))

        fourth.get("childReg1") should be(Option(0d))
        fourth.get("childReg2") should be(Option(0d))
        fourth.get("childReg3") should be(Option(0d))
        fourth.get("assessLanguage") should be(Option(0d))
        fourth.get("languageLevel") should be(Option(0d))
        fourth.get("selectNumeracyQ1") should be(Option(0d))
        fourth.get("assessNumeracyQ1") should be(Option(0d))
        fourth.get("selectNumeracyQ2") should be(Option(0d))
        fourth.get("assessNumeracyQ2") should be(Option(0d))
        fourth.get("assessNumeracyQ3") should be(Option(0d))
        fourth.get("scorecard") should be(Option(0d))
        fourth.get("summary") should be(Option(0d))
    }
    //-----test7--
    it should "check summary events , all having non-zero value" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/allAserEventsTest.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(2)

        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should not be (Option(0d))
        first.get("surveyCodePage") should not be (Option(0d))
        first.get("childReg1") should not be (Option(0d))
        first.get("childReg2") should not be (Option(0d))
        first.get("childReg3") should not be (Option(0d))
        first.get("assessLanguage") should not be (Option(0d))
        first.get("languageLevel") should not be (Option(0d))
        first.get("selectNumeracyQ1") should not be (Option(0d))
        first.get("assessNumeracyQ1") should not be (Option(0d))
        first.get("selectNumeracyQ2") should not be (Option(0d))
        first.get("assessNumeracyQ2") should not be (Option(0d))
        first.get("assessNumeracyQ3") should not be (Option(0d))
        first.get("scorecard") should not be (Option(0d))
        first.get("summary") should not be (Option(0d))

        sec.get("activationKeyPage") should not be (Option(0d))
        sec.get("surveyCodePage") should not be (Option(0d))
        sec.get("childReg1") should not be (Option(0d))
        sec.get("childReg2") should not be (Option(0d))
        sec.get("childReg3") should not be (Option(0d))
        sec.get("assessLanguage") should not be (Option(0d))
        sec.get("languageLevel") should not be (Option(0d))
        sec.get("selectNumeracyQ1") should not be (Option(0d))
        sec.get("assessNumeracyQ1") should not be (Option(0d))
        sec.get("selectNumeracyQ2") should not be (Option(0d))
        sec.get("assessNumeracyQ2") should not be (Option(0d))
        sec.get("assessNumeracyQ3") should not be (Option(0d))
        sec.get("scorecard") should not be (Option(0d))
        sec.get("summary") should not be (Option(0d))
    }
    //-----test8--
    it should "check summary events, not having any reg. pages" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/noRegPages.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should be(Option(0d))
        first.get("surveyCodePage") should be(Option(0d))
        first.get("childReg1") should be(Option(0d))
        first.get("childReg2") should be(Option(0d))
        first.get("childReg3") should be(Option(0d))
    }
    //-----test9--
    it should "check summary events, having three reg. pages" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/3nextButton.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should not be (Option(0d))
        first.get("surveyCodePage") should not be (Option(0d))
        first.get("childReg1") should not be (Option(0d))
        first.get("childReg2") should be(Option(0d))
        first.get("childReg3") should be(Option(0d))
    }
    //-----test10--
    it should "check summary events, only having four assess pages" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/OE_ASSESS.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should be(Option(0d))
        first.get("surveyCodePage") should be(Option(0d))
        first.get("childReg1") should be(Option(0d))
        first.get("childReg2") should be(Option(0d))
        first.get("childReg3") should be(Option(0d))
        first.get("assessLanguage") should not be (Option(0d))
        first.get("languageLevel") should be(Option(0d))
        first.get("selectNumeracyQ1") should be(Option(0d))
        first.get("assessNumeracyQ1") should not be (Option(0d))
        first.get("selectNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ2") should not be (Option(0d))
        first.get("assessNumeracyQ3") should not be (Option(0d))
        first.get("scorecard") should be(Option(0d))
        first.get("summary") should be(Option(0d))
    }

    //-----test11--
    it should "check summary events, only having three Reg. pages" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/only3NextButtonPressed.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should not be (Option(0d))
        first.get("surveyCodePage") should not be (Option(0d))
        first.get("childReg1") should not be (Option(0d))
        first.get("childReg2") should be(Option(0d))
        first.get("childReg3") should be(Option(0d))
        first.get("assessLanguage") should be(Option(0d))
        first.get("languageLevel") should be(Option(0d))
        first.get("selectNumeracyQ1") should be(Option(0d))
        first.get("assessNumeracyQ1") should be(Option(0d))
        first.get("selectNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ3") should be(Option(0d))
        first.get("scorecard") should be(Option(0d))
        first.get("summary") should be(Option(0d))
    }
    //-----test12--
    it should "check summary events, only having all Reg. pages" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/only5NextButtonPressed.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should not be (Option(0d))
        first.get("surveyCodePage") should not be (Option(0d))
        first.get("childReg1") should not be (Option(0d))
        first.get("childReg2") should not be (Option(0d))
        first.get("childReg3") should not be (Option(0d))
        first.get("assessLanguage") should be(Option(0d))
        first.get("languageLevel") should be(Option(0d))
        first.get("selectNumeracyQ1") should be(Option(0d))
        first.get("assessNumeracyQ1") should be(Option(0d))
        first.get("selectNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ3") should be(Option(0d))
        first.get("scorecard") should be(Option(0d))
        first.get("summary") should be(Option(0d))
    }
    //-----test13--
    it should "check two summary events, all having zero field value" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/twoOE_START_only.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(2)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        val sec = me(1).edata.eks.asInstanceOf[HashTrieMap[String, Double]];

        first.get("activationKeyPage") should be(Option(0d))
        first.get("surveyCodePage") should be(Option(0d))
        first.get("childReg1") should be(Option(0d))
        first.get("childReg2") should be(Option(0d))
        first.get("childReg3") should be(Option(0d))
        first.get("assessLanguage") should be(Option(0d))
        first.get("languageLevel") should be(Option(0d))
        first.get("selectNumeracyQ1") should be(Option(0d))
        first.get("assessNumeracyQ1") should be(Option(0d))
        first.get("selectNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ2") should be(Option(0d))
        first.get("assessNumeracyQ3") should be(Option(0d))
        first.get("scorecard") should be(Option(0d))
        first.get("summary") should be(Option(0d))

        sec.get("activationKeyPage") should be(Option(0d))
        sec.get("surveyCodePage") should be(Option(0d))
        sec.get("childReg1") should be(Option(0d))
        sec.get("childReg2") should be(Option(0d))
        sec.get("childReg3") should be(Option(0d))
        sec.get("assessLanguage") should be(Option(0d))
        sec.get("languageLevel") should be(Option(0d))
        sec.get("selectNumeracyQ1") should be(Option(0d))
        sec.get("assessNumeracyQ1") should be(Option(0d))
        sec.get("selectNumeracyQ2") should be(Option(0d))
        sec.get("assessNumeracyQ2") should be(Option(0d))
        sec.get("assessNumeracyQ3") should be(Option(0d))
        sec.get("scorecard") should be(Option(0d))
        sec.get("summary") should be(Option(0d))
    }
    //-----test14--
    it should "check summary events from 'oe_assessLen0.txt' file, all having non-zero value" in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/oe_assessLen0.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        val me = rdd2.collect();
        me.length should be(1)
        val first = me(0).edata.eks.asInstanceOf[HashTrieMap[String, Double]];
        first.get("activationKeyPage") should not be (Option(0d))
        first.get("surveyCodePage") should not be (Option(0d))
        first.get("childReg1") should not be (Option(0d))
        first.get("childReg2") should not be (Option(0d))
        first.get("childReg3") should not be (Option(0d))
        first.get("assessLanguage") should not be (Option(0d))
        first.get("languageLevel") should not be (Option(0d))
        first.get("selectNumeracyQ1") should not be (Option(0d))
        first.get("assessNumeracyQ1") should not be (Option(0d))
        first.get("selectNumeracyQ2") should not be (Option(0d))
        first.get("assessNumeracyQ2") should not be (Option(0d))
        first.get("assessNumeracyQ3") should not be (Option(0d))
        first.get("scorecard") should not be (Option(0d))
        first.get("summary") should not be (Option(0d))
    }

    it should " generate an event when input having only (OE_ASSESS of length=0) events " in {
        val event = loadFile[Event]("src/test/resources/aserlite-screen-summary/zeroEvents.txt");
        val rdd = DataFilter.filter(event, Filter("eventId", "IN", Option(List("OE_START", "OE_INTERACT", "OE_ASSESS", "OE_LEVEL_SET", "OE_END"))));
        val rdd2 = AserScreenSummaryModel.execute(rdd, Option(Map("modelVersion" -> "1.1", "modelId" -> "AserScreenerSummary")));
        rdd2.collect().length should be (1)
    }
    //--------
}