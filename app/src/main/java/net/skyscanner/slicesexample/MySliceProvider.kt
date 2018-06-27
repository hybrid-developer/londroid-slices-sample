package net.skyscanner.slicesexample

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import androidx.slice.builders.cell
import androidx.slice.builders.gridRow
import androidx.slice.builders.header
import androidx.slice.builders.list
import androidx.slice.builders.seeMoreCell

class MySliceProvider : SliceProvider() {
    override fun onBindSlice(sliceUri: Uri?): Slice? {
        if (sliceUri == null || sliceUri.path == null) {
            return null
        }
        return when (sliceUri.path) {
            "/inspiration" -> createInspirationSlice(sliceUri)
            "/londroid" -> createLondroidSlice(sliceUri)
            else -> null
        }
    }

    val pi: PendingIntent
        get() = PendingIntent.getActivity(context, 0, Intent(), 0)

    private fun createLondroidSlice(sliceUri: Uri): Slice? =
        list(context, sliceUri, ListBuilder.INFINITY) {
            addAction(
                SliceAction(
                    pi,
                    IconCompat.createWithResource(context, R.drawable.ic_location_on_black_24dp),
                    "Check Address"
                )
            )
            addAction(
                SliceAction(
                    pi,
                    IconCompat.createWithResource(context, R.drawable.ic_message_black_24dp),
                    "Message host"
                )
            )
            header {
                setTitle("Upcoming meetup: Londroid")
                setSubtitle("Jun 26 - 75 guests")
            }
            gridRow {
                cell {
                    addImage(IconCompat.createWithResource(context, R.drawable.londroid), ListBuilder.LARGE_IMAGE)
                }
            }
            gridRow {
                cell {
                    addTitleText("Check In")
                    addText("06:00 PM")
                }
                cell {

                    addTitleText("Check Out")
                    addText("09:00 PM")
                }
            }
        }

    private fun createInspirationSlice(sliceUri: Uri): Slice? =
        list(context, sliceUri, ListBuilder.INFINITY) {
            header {
                setTitle("Be inspired")
                setSubtitle("Cheap summer breaks")
            }
            gridRow {
                cell {
                    addTitleText("Greece")
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground), ListBuilder.LARGE_IMAGE)
                    addText("£96")
                }
                cell {
                    addTitleText("Thailand")
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground), ListBuilder.LARGE_IMAGE)
                    addText("£260")
                }
                cell {
                    addTitleText("Iceland")
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground), ListBuilder.LARGE_IMAGE)
                    addText("£360")
                }
                cell {
                    addTitleText("Edinburgh")
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground), ListBuilder.LARGE_IMAGE)
                    addText("£25")
                }
                cell {
                    addTitleText("Estonia")
                    addImage(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground), ListBuilder.LARGE_IMAGE)
                    addText("£63")
                }
                seeMoreCell {
                    addTitleText("See more")
                }
            }
        }

    override fun onCreateSliceProvider(): Boolean = true
}
