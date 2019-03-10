package boaventura.com.devel.br.flutteraudioquery.loaders.tasks;

import android.os.AsyncTask;

/**
 *
 * This is the base class for classes that will do load data job on
 * background thread.
 *
 * @param <T> return type data
 */
public abstract class AbstractLoadTask<T> extends AsyncTask<Void, Void, T>
{

    //private MethodChannel.Result m_result;
    private String m_selection, m_sortOrder;
    private String[] m_selectionArgs;


    public AbstractLoadTask(final String selection, final String[] selectionArgs,
                            final String sortOrder){
        this.m_selection =selection;
        this.m_sortOrder =sortOrder;
        this.m_selectionArgs = selectionArgs;
    }


    /**
     * Interface of method that will make your background query
     * @param selection Selection params [WHERE param = "?="].
     * @param selectionArgs Selection args [paramValue].
     * @param sortOrder Your query sort order.
     * @return return you generic data type.
     */
    protected abstract T loadData(final String selection,
                                  final String[] selectionArgs, final String sortOrder);

    @Override
    protected T doInBackground(Void... voids) {
        return loadData(m_selection, m_selectionArgs, m_sortOrder);
    }

    @Override
    protected void onPostExecute(T data){
        m_selectionArgs = null;
        m_selection = null;
        m_sortOrder = null;
    }
}
