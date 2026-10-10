package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class SuratMasuk extends Surat {

    private static int urutanSuratBerikutnya = 1;

    private String tanggalMasukSurat;
    private String pengirim;

    public SuratMasuk(
            int urutanSurat,
            String nomorSurat,
            String perihal,
            String tanggalMasukSurat,
            String pengirim) {

        super(urutanSurat, nomorSurat, perihal);

        setTanggalMasukSurat(tanggalMasukSurat);
        setPengirim(pengirim);
    }
    
    // Constructor untuk membuat surat masuk baru
    public SuratMasuk(
            String nomorSurat,
            String perihal,
            String tanggalMasukSurat,
            String pengirim) {

        super(nomorSurat, perihal);

        setTanggalMasukSurat(tanggalMasukSurat);
        setPengirim(pengirim);
    }

    public String getTanggalMasukSurat() {
        return tanggalMasukSurat;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setTanggalMasukSurat(String tanggalMasukSurat) {
        if (tanggalMasukSurat != null
                && !tanggalMasukSurat.trim().isEmpty()) {

            this.tanggalMasukSurat = tanggalMasukSurat.trim();

        } else {
            System.out.println("Tanggal masuk tidak boleh kosong.");
        }
    }

    public void setPengirim(String pengirim) {
        if (pengirim != null
                && !pengirim.trim().isEmpty()) {

            this.pengirim = pengirim.trim();

        } else {
            System.out.println("Pengirim tidak boleh kosong.");
        }
    }

    @Override
    public void tampilkanDaftarSurat(){

        System.out.println("==============================");
        System.out.println("DAFTAR SURAT MASUK");
        tampilkanInfoDasar();
        System.out.println("Tanggal Masuk : " + tanggalMasukSurat);
        System.out.println("Pengirim      : " + pengirim);
        System.out.println("==============================");
    }
}
