package com.mkdika.zkmvvmcrud8.web.zk.component;


import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.web.zk.template.GenericVm;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class BrowseData extends GenericVm {

    private ListModelList<TbPerson> persons;
    private TbPerson personSelected;

    @Init
    public void init() throws Exception {
        setPersons(new ListModelList<>(getService().findAll(TbPerson.class,"firstname")  ));
    }

    @Command
    public void listboxSelected(@BindingParam("window") Window window) {
        if (personSelected != null) {
            Map returnArgs = new HashMap();
            returnArgs.put("personSelected", personSelected);
            BindUtils.postGlobalCommand(null, null, "Index$browseSelected", returnArgs);
            window.onClose();
        }
    }
    
    @Command
    @SmartNotifyChange({"persons"})
    public void search(@BindingParam("key") String key) {        
        setPersons(new ListModelList<>(getService().searchTbPerson(key)));
    }

    public ListModelList<TbPerson> getPersons() {
        return persons;
    }

    public void setPersons(ListModelList<TbPerson> persons) {
        this.persons = persons;
    }

    public TbPerson getPersonSelected() {
        return personSelected;
    }

    public void setPersonSelected(TbPerson personSelected) {
        this.personSelected = personSelected;
    }
}
