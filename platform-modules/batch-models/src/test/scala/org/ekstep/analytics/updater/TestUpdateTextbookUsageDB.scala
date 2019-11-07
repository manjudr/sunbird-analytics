package org.ekstep.analytics.updater

import org.ekstep.analytics.model.SparkSpec
import org.ekstep.analytics.framework.ProfileEvent
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector
import org.ekstep.analytics.util.Constants
import org.ekstep.analytics.creation.model.CreationEvent
import org.ekstep.analytics.framework.DerivedEvent
import org.ekstep.analytics.framework.util.JSONUtils
import org.scalatest.Ignore
/**
 * @author yuva
 */
@Ignore @deprecated
class TestUpdateTextbookUsageDB extends SparkSpec(null) {

    override def beforeAll() {
        super.beforeAll()
        val connector = CassandraConnector(sc.getConf);
        val session = connector.openSession();
        session.execute("TRUNCATE " + Constants.CREATION_METRICS_KEY_SPACE_NAME + "." + Constants.TEXTBOOK_SESSION_METRICS_FACT);
    }

    "UpdateTextbookSessionsDB" should "store data in textbook_session_metrics_fact" in {
        val rdd = loadFile[DerivedEvent]("src/test/resources/textbook-session-updater/textbook-usage-summary1.log");
        UpdateTextbookUsageDB.execute(rdd, None);

        val dayRecord = sc.cassandraTable[TextbookSessionMetricsFact](Constants.CREATION_METRICS_KEY_SPACE_NAME, Constants.TEXTBOOK_SESSION_METRICS_FACT).where("d_period=?", 20170530).first()
        // check for day record
        dayRecord.total_ts should be(15449.0)
        dayRecord.unique_users_count should be(1)
        dayRecord.total_sessions should be(1)
        dayRecord.unit_summary.get("added_count").get should be(14)
        dayRecord.unit_summary.get("deleted_count").get should be(7)
        dayRecord.unit_summary.get("modified_count").get should be(7)
        dayRecord.lesson_summary.get("added_count").get should be(14)
        dayRecord.lesson_summary.get("deleted_count").get should be(7)
        dayRecord.lesson_summary.get("modified_count").get should be(7)

        // check for week record
        val weekRecord = sc.cassandraTable[TextbookSessionMetricsFact](Constants.CREATION_METRICS_KEY_SPACE_NAME, Constants.TEXTBOOK_SESSION_METRICS_FACT).where("d_period=?", 2017721).first()
        weekRecord.total_ts should be(77245.0)
        weekRecord.unique_users_count should be(5)
        weekRecord.total_sessions should be(5)
        weekRecord.unit_summary.get("added_count").get should be(70)
        weekRecord.unit_summary.get("deleted_count").get should be(35)
        weekRecord.unit_summary.get("modified_count").get should be(35)
        weekRecord.lesson_summary.get("added_count").get should be(70)
        weekRecord.lesson_summary.get("deleted_count").get should be(35)
        weekRecord.lesson_summary.get("modified_count").get should be(35)

        // check for month record
        val monthRecord = sc.cassandraTable[TextbookSessionMetricsFact](Constants.CREATION_METRICS_KEY_SPACE_NAME, Constants.TEXTBOOK_SESSION_METRICS_FACT).where("d_period=?", 201705).first
        monthRecord.total_ts should be(123592.0)
        monthRecord.unique_users_count should be(8)
        monthRecord.total_sessions should be(8)
        monthRecord.unit_summary.get("added_count").get should be(112)
        monthRecord.unit_summary.get("deleted_count").get should be(56)
        monthRecord.unit_summary.get("modified_count").get should be(56)
        monthRecord.lesson_summary.get("added_count").get should be(112)
        monthRecord.lesson_summary.get("deleted_count").get should be(56)
        monthRecord.lesson_summary.get("modified_count").get should be(56)

        // check for cumulative record
        val cumRecord = sc.cassandraTable[TextbookSessionMetricsFact](Constants.CREATION_METRICS_KEY_SPACE_NAME, Constants.TEXTBOOK_SESSION_METRICS_FACT).where("d_period=?", 0).first
        cumRecord.total_ts should be(123592.0)
        cumRecord.unique_users_count should be(8)
        cumRecord.total_sessions should be(8)
        cumRecord.unit_summary.get("added_count").get should be(112)
        cumRecord.unit_summary.get("deleted_count").get should be(56)
        cumRecord.unit_summary.get("modified_count").get should be(56)
        cumRecord.lesson_summary.get("added_count").get should be(112)
        cumRecord.lesson_summary.get("deleted_count").get should be(56)
        cumRecord.lesson_summary.get("modified_count").get should be(56)
    }
}