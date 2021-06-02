package com.saket.spacex.Repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.saket.spacex.Dao.CrewDao;
import com.saket.spacex.model.Crew;

import java.util.ArrayList;
import java.util.List;

public class CrewRepository {

    private CrewDao crewDao;
    private LiveData<List<Crew>> getAll;
    public CrewRepository(CrewDao crewDao)
    {
        this.crewDao=crewDao;
        getAll=crewDao.getCrewList();
    }
    public void  insertAll(List<Crew> crewList)
    {
         new InsertAsyncTask(crewDao).execute(crewList);
    }

    public void deleteAll()
    {
        crewDao.deleteTable();
    }

    public LiveData<List<Crew>> getCrewList()
    {
        return getAll;
    }

    class InsertAsyncTask extends AsyncTask<List<Crew>, Void,Void>{

        private CrewDao crewDao;
        InsertAsyncTask(CrewDao crewDao)
        {
            this.crewDao=crewDao;
        }
        @Override
        protected Void doInBackground(List<Crew>... Lists) {
            crewDao.insertAll(Lists[0]);
            return null;
        }
    }
}
