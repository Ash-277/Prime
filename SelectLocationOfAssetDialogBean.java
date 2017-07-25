//
// by Alexandra Mikhaylova on 2017

package custom.webclient.beans.asset.e.dialog;

import java.rmi.RemoteException;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.controller.Utility;
import psdi.webclient.system.controller.WebClientEvent;

public class SelectLocationOfAssetDialogBean extends DataBean {
    public SelectLocationOfAssetDialogBean() {
    }



    public int selectrecord() throws MXException, RemoteException {
        DataBean appBean = Utility.getDataSource(this.sessionContext, this.app.getAppHandler());
        MboRemote assetMbo = appBean.getMbo();
        MboRemote selectionMbo = this.getMbo();

        MboRemote existMbo = assetMbo.getMboSet("GROT_EXIST").getMbo();
        if (existMbo.isNull("location"))
            this.clientSession.showMessageBox(this.clientSession.getCurrentEvent(), "Ошибка",
                    "Перед выполнением данного действия необходимо заполнить границы ответственности подразделения", 1);
        else

        assetMbo.setValue("location", selectionMbo.getString("location"), 11L);
        this.app.getDataBean().fireStructureChangedEvent();
        Utility.sendEvent(new WebClientEvent("dialogclose", this.app.getCurrentPageId(), (Object)null, this.sessionContext));

        return 1;
    }
}
