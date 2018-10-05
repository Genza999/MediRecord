package mediRecord.mediRecord.mediRecord.mediRecord.db;

import mediRecord.mediRecord.mediRecord.mediRecord.data.PatientDetails;

/**
 * Created by Genza on 10/02/2018.
 */

public interface GetDataListener {

    public void onStart();
    public void onSuccess(PatientDetails patientDetails);
    public void onFailure();
}
