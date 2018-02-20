package com.eezo.hrd.controllers;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.*;

@ManagedBean
@SessionScoped
public class LiteratureController implements Serializable {
    private StreamedContent file1;
    private StreamedContent file2;
    private StreamedContent file3;
    private StreamedContent file4;
    private StreamedContent file5;
    private StreamedContent file6;
    private StreamedContent file7;
    private StreamedContent file8;

    public LiteratureController() {
        InputStream stream1 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/Vytiag.pdf");
        file1 = new DefaultStreamedContent(stream1, "application/octet-stream", "Vytiag.pdf");

        InputStream stream2 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/Vypiska271014newGD_Fedin.pdf");
        file2 = new DefaultStreamedContent(stream2, "application/octet-stream", "Vypiska271014newGD_Fedin.pdf");

        InputStream stream3 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/vypiska_EDR_21.07.2014.pdf");
        file3 = new DefaultStreamedContent(stream3, "application/octet-stream", "vypiska_EDR_21.07.2014.pdf");

        InputStream stream4 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/dogovor_arendy_s_NSK_Agro.doc");
        file4 = new DefaultStreamedContent(stream4, "application/octet-stream", "dogovor_arendy_s_NSK_Agro.doc");

        InputStream stream5 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/dogovor_arendy_chernomorets.doc");
        file5 = new DefaultStreamedContent(stream5, "application/octet-stream", "dogovor_arendy_chernomorets.doc");

        InputStream stream6 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/Prikas_o_sosdanii_hozraschetnogo.doc");
        file6 = new DefaultStreamedContent(stream6, "application/octet-stream", "Prikas_o_sosdanii_hozraschetnogo.doc");

        InputStream stream7 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/protokol_OSY_SMG_-_obrannya_Fedina.pdf");
        file7 = new DefaultStreamedContent(stream7, "application/octet-stream", "protokol_OSY_SMG_-_obrannya_Fedina.pdf");

        InputStream stream8 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/files/Statut_10.07.2014.pdf");
        file8 = new DefaultStreamedContent(stream8, "application/octet-stream", "Statut_10.07.2014.pdf");
    }

    public StreamedContent getFile1() {
        return file1;
    }

    public StreamedContent getFile2() {
        return file2;
    }

    public StreamedContent getFile3() {
        return file3;
    }

    public StreamedContent getFile4() {
        return file4;
    }

    public StreamedContent getFile5() {
        return file5;
    }

    public StreamedContent getFile6() {
        return file6;
    }

    public StreamedContent getFile7() {
        return file7;
    }

    public StreamedContent getFile8() {
        return file8;
    }
}
