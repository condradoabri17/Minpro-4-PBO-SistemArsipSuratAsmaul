/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */

public abstract class Surat implements ArsipSurat {

    protected int urutanSurat;
    protected String nomorSurat;
    protected String perihal;

    public Surat(int urutanSurat, String nomorSurat, String perihal) {
        setUrutanSurat(urutanSurat);
        setNomorSurat(nomorSurat);
        setPerihal(perihal);
    }

    public Surat(String nomorSurat, String perihal) {
        this.urutanSurat = 0;
        setNomorSurat(nomorSurat);
        setPerihal(perihal);
    }

    public int getUrutanSurat() {
        return urutanSurat;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setUrutanSurat(int urutanSurat) {
        if (urutanSurat > 0) {
            this.urutanSurat = urutanSurat;
        } else {
            System.out.println("Urutan surat harus lebih dari 0.");
        }
    }

    public void setNomorSurat(String nomorSurat) {
        if (nomorSurat != null && !nomorSurat.trim().isEmpty()) {
            this.nomorSurat = nomorSurat.trim();
        } else {
            System.out.println("Nomor surat tidak boleh kosong.");
        }
    }

    public void setPerihal(String perihal) {
        if (perihal != null && !perihal.trim().isEmpty()) {
            this.perihal = perihal.trim();
        } else {
            System.out.println("Perihal tidak boleh kosong.");
        }
    }

    protected void tampilkanInfoDasar() {
        System.out.println("Urutan Surat : " + urutanSurat);
        System.out.println("Nomor Surat  : " + nomorSurat);
        System.out.println("Perihal      : " + perihal);
    }

    @Override
    public abstract void tampilkanDaftarSurat();

}
