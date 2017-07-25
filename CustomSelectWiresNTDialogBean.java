package custom.webclient.beans.asset.e.dialog;

import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.util.MXException;
import psdi.webclient.system.beans.DataBean;
import psdi.webclient.system.beans.MultiselectDataBean;
import psdi.webclient.system.controller.Utility;
import psdi.webclient.system.controller.WebClientEvent;

import java.rmi.RemoteException;

/**
 * Created by Aleksandra.Mihaylova on 14.03.2017.
 */
public class CustomSelectWiresNTDialogBean extends MultiselectDataBean {

    public int addrecord () throws MXException, RemoteException {

        DataBean appBean = Utility.getDataSource(this.sessionContext, this.app.getAppHandler());
        MboRemote ankMbo = appBean.getMbo();

        MboSetRemote relationSet = this.getMboSet();

        if (ankMbo.isNull("TYPE_PODVESKI.kp.label_wire3")&&ankMbo.isNull("TYPE_PODVESKI.kp.name")) {
            this.clientSession.showMessageBox(this.clientSession.getCurrentEvent(), "Ошибка",
                    "Перед добавлением провода необходимо выбрать тип подвески", 1);

        }
        else if (ankMbo.isNull("TYPE_PODVESKI.kp.label_wire3")&&!ankMbo.isNull("TYPE_PODVESKI.kp.name")) {
            this.clientSession.showMessageBox(this.clientSession.getCurrentEvent(), "Ошибка",
                    "Для выбранного типа подвески не предусмотрен несущий трос. Обратитесь к справочнику.", 1);
        }
        else {

            MboRemote relationMbo = relationSet.add();

            relationMbo.setValue("wireassetnum", ankMbo.getString("assetnum"), 11L);
            relationMbo.setValue("BEARINGWIRETYPE", ankMbo.getString("TYPE_PODVESKI.kp.label_wire3"), 11L);
            relationMbo.setValue("siteid", ankMbo.getString("siteid"), 11L);
        }

        this.app.getDataBean().fireStructureChangedEvent();
        Utility.sendEvent(new WebClientEvent("dialogclose", this.app.getCurrentPageId(), (Object)null, this.sessionContext));

        return 1;
    }
}

