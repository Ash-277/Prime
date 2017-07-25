package custom.webclient.beans.asset.e;

import java.rmi.RemoteException;

import custom.app.system.Coordinates;
import psdi.mbo.MboRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;

/**
 * Created  on 07.02.2017.
 */
public class TowersTableBean extends DataBean {
    public TowersTableBean() {
    }

    public void setValue(int nRow, String attribute, String value, long accessModifier) throws MXException {
        try {
            MboRemote mbo = mboSetRemote.getMbo(nRow);
            if (mbo.getMboValueData(attribute).getData().compareTo(value) == 0)
                return;
            if (attribute.equalsIgnoreCase("KM") || attribute.equalsIgnoreCase("M") || attribute.equalsIgnoreCase("PK"))
            {
                if (attribute.equalsIgnoreCase("KM"))
                {
                    mbo.setValue("SOURCESTARTMEASURE",  Coordinates.getMeasure(Integer.parseInt(value), mbo.getInt("pk"), mbo.getInt("m")), 11L);
                }
                if (attribute.equalsIgnoreCase("PK"))
                {
                    mbo.setValue("SOURCESTARTMEASURE",  Coordinates.getMeasure(mbo.getInt("KM"), Integer.parseInt(value), mbo.getInt("m")), 11L);
                }
                if (attribute.equalsIgnoreCase("M"))
                {
                    mbo.setValue("SOURCESTARTMEASURE",  Coordinates.getMeasure(mbo.getInt("KM"), mbo.getInt("pk"), Integer.parseInt(value)), 11L);
                }
            }
            if (attribute.equalsIgnoreCase("KMK") || attribute.equalsIgnoreCase("PKK") || attribute.equalsIgnoreCase("PMSK"))
            {
                if (attribute.equalsIgnoreCase("KMK"))
                {
                    mbo.setValue("SOURCEENDMEASURE",  Coordinates.getMeasure(Integer.parseInt(value), mbo.getInt("PKK"), mbo.getInt("PMSK")), 11L);
                }
                if (attribute.equalsIgnoreCase("PKK"))
                {
                    mbo.setValue("SOURCEENDMEASURE",  Coordinates.getMeasure(mbo.getInt("KMK"), Integer.parseInt(value), mbo.getInt("PMSK")), 11L);
                }
                if (attribute.equalsIgnoreCase("PMSK"))
                {
                    mbo.setValue("SOURCEENDMEASURE",  Coordinates.getMeasure(mbo.getInt("KMK"), mbo.getInt("PKK"), Integer.parseInt(value)), 11L);
                }
            }

            super.setValue(nRow, attribute, value, accessModifier);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return;
    }
}