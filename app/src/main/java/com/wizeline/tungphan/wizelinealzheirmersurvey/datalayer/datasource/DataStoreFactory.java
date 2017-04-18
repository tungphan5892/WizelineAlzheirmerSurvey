package com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.network.NetworkService;
import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.network.NetworkServiceImpl;
import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.sqlitedatabase.DatabaseHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link DataStore}.
 */
@Singleton
public class DataStoreFactory {
    private final boolean USING_LOCAL_DATABASE = true;  // set false when we want to call
    //the api service or other purpose
    private final Context context;
    private final DatabaseHelper databaseHelper;

    @Inject
    DataStoreFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
        this.databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Create {@link DataStore} from a user id.
     */
    public DataStore create() {
        DataStore dataStore;
        if (USING_LOCAL_DATABASE) {
            dataStore = new LocalDataStore(databaseHelper);
        } else {
            dataStore = createCloudDataStore();
        }
        return dataStore;
    }

    /**
     * Create {@link DataStore} to retrieve data from the Cloud.
     */
    public DataStore createCloudDataStore() {
        final NetworkService networkService = new NetworkServiceImpl();
        return new CloudDataStore(networkService);
    }
}
