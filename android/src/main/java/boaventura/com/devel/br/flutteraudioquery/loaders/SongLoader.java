package boaventura.com.devel.br.flutteraudioquery.loaders;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boaventura.com.devel.br.flutteraudioquery.loaders.tasks.AbstractLoadTask;
import io.flutter.plugin.common.MethodChannel;

public class SongLoader {

    final private ContentResolver m_resolver;
    private static final String TAG = "MDBG";
    private static final String GENRE_NAME = "genre_name";

    //private static final String MOST_PLAYED = "most_played"; //undocumented column
    //private static final String RECENTLY_PLAYED = "recently_played"; // undocumented column

    private static final String[] SONG_PROJECTION = {
            MediaStore.Audio.Media._ID,// row id
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            GENRE_NAME,
            MediaStore.Audio.Media.IS_MUSIC,
            MediaStore.Audio.Media.IS_PODCAST,
            MediaStore.Audio.Media.IS_RINGTONE,
            MediaStore.Audio.Media.IS_ALARM,
            MediaStore.Audio.Media.IS_NOTIFICATION,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.DURATION, // duration of the audio file in ms
            MediaStore.Audio.Media.BOOKMARK, // position, in ms, where playback was at in last stopped
            MediaStore.Audio.Media.DATA, // file data path
            MediaStore.Audio.Media.SIZE, // string with file size in bytes

    };

    public SongLoader(final Context context){
        m_resolver = context.getContentResolver();
    }


    public void getSongs( final MethodChannel.Result result ){
        createLoadTask( result,null,null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }


    public void getSongsFromAlbum(final MethodChannel.Result result, final String albumId){
       createLoadTask( result, MediaStore.Audio.Media.ALBUM_ID + " =? ",
                new String[] {albumId},
               MediaStore.Audio.Media.DEFAULT_SORT_ORDER );
    }


    public void getSongsFromArtist(final MethodChannel.Result result, final String artistName){

        createLoadTask(result, MediaStore.Audio.Media.ARTIST + " =? ",
                new String[] { artistName }, MediaStore.Audio.Media.DEFAULT_SORT_ORDER );
    }

    private void createLoadTask(MethodChannel.Result result, final String selection, final String [] selectionArgs,
                                final String sortOrder){

        new SongTaskLoad(result, m_resolver, selection, selectionArgs,sortOrder).execute();

    }

    private static class SongTaskLoad extends AbstractLoadTask< List< Map<String,Object> > > {

        private MethodChannel.Result result;
        private ContentResolver resolver;

        /**
         *
         * @param result
         * @param resolver
         * @param selection
         * @param selectionArgs
         * @param sortOrder
         */
        SongTaskLoad(MethodChannel.Result result, ContentResolver resolver, String selection,
                     String[] selectionArgs, String sortOrder){

            super(selection, selectionArgs, sortOrder);
            this.resolver = resolver;
            this.result =result;
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> map) {
            super.onPostExecute(map);
            result.success(map);
            this.resolver = null;
            this.result = null;
        }

        @Override
        protected List< Map<String,Object> > loadData(
                final String selection, final String [] selectionArgs,
                final String sortOrder ){

            List< Map<String, Object> > dataList = new ArrayList<>();

            Cursor cursor = resolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    SongLoader.SONG_PROJECTION, selection, selectionArgs, sortOrder );

            if (cursor != null){

                while( cursor.moveToNext() ){
                    Map<String, Object> songData = new HashMap<>();
                    for (String column: cursor.getColumnNames())
                        songData.put(column, cursor.getString( cursor.getColumnIndex( column )) );

                    dataList.add( songData );
                }

                cursor.close();
            }

            return dataList;
        }
    }
}


/*
 *  NON DOCUMENTED MEDIA COLUMNS
 *
 * album_artist
 * duration
 * genre_name
 * recently_played
 * most_played
 *
 */

/*
 *          ALL MEDIA COLUMNS
 *
 * I/MDBG    (15056): Column: _id
 * I/MDBG    (15056): Column: _data
 * I/MDBG    (15056): Column: _display_name
 * I/MDBG    (15056): Column: _size
 * I/MDBG    (15056): Column: mime_type
 * I/MDBG    (15056): Column: date_added
 * I/MDBG    (15056): Column: is_drm
 * I/MDBG    (15056): Column: date_modified
 * I/MDBG    (15056): Column: title
 * I/MDBG    (15056): Column: title_key
 * I/MDBG    (15056): Column: duration
 * I/MDBG    (15056): Column: artist_id
 * I/MDBG    (15056): Column: composer
 * I/MDBG    (15056): Column: album_id
 * I/MDBG    (15056): Column: track
 * I/MDBG    (15056): Column: year
 * I/MDBG    (15056): Column: is_ringtone
 * I/MDBG    (15056): Column: is_music
 * I/MDBG    (15056): Column: is_alarm
 * I/MDBG    (15056): Column: is_notification
 * I/MDBG    (15056): Column: is_podcast
 * I/MDBG    (15056): Column: bookmark
 * I/MDBG    (15056): Column: album_artist
 * I/MDBG    (15056): Column: is_sound
 * I/MDBG    (15056): Column: year_name
 * I/MDBG    (15056): Column: genre_name
 * I/MDBG    (15056): Column: recently_played
 * I/MDBG    (15056): Column: most_played
 * I/MDBG    (15056): Column: recently_added_remove_flag
 * I/MDBG    (15056): Column: is_favorite
 * I/MDBG    (15056): Column: bucket_id
 * I/MDBG    (15056): Column: bucket_display_name
 * I/MDBG    (15056): Column: recordingtype
 * I/MDBG    (15056): Column: latitude
 * I/MDBG    (15056): Column: longitude
 * I/MDBG    (15056): Column: addr
 * I/MDBG    (15056): Column: langagecode
 * I/MDBG    (15056): Column: is_secretbox
 * I/MDBG    (15056): Column: is_memo
 * I/MDBG    (15056): Column: label_id
 * I/MDBG    (15056): Column: weather_ID
 * I/MDBG    (15056): Column: sampling_rate
 * I/MDBG    (15056): Column: bit_depth
 * I/MDBG    (15056): Column: recorded_number
 * I/MDBG    (15056): Column: recording_mode
 * I/MDBG    (15056): Column: is_ringtone_theme
 * I/MDBG    (15056): Column: is_notification_theme
 * I/MDBG    (15056): Column: is_alarm_theme
 * I/MDBG    (15056): Column: datetaken
 * I/MDBG    (15056): Column: artist_id:1
 * I/MDBG    (15056): Column: artist_key
 * I/MDBG    (15056): Column: artist
 * I/MDBG    (15056): Column: album_id:1
 * I/MDBG    (15056): Column: album_key
 * I/MDBG    (15056): Column: album
 *
 *
 */
