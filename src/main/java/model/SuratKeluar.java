package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class SuratKeluar extends Surat {

    private static int urutanSuratBerikutnya = 1;

    private static final int MIN_KATEGORI = 1;
    private static final int MAX_KATEGORI = 13;

    private int kategoriSurat;
    private String tanggalKeluarSurat;
    private String penerima;

    // Constructor untuk membuat surat keluar baru
    public SuratKeluar(
            String perihal,
            int kategoriSurat,
            String tanggalKeluarSurat,
            String penerima) {

        super(
                urutanSuratBerikutnya,
                "",
                perihal
        );

        setKategoriSurat(kategoriSurat);
        setTanggalKeluarSurat(tanggalKeluarSurat);
        setPenerima(penerima);

        String[] tanggal = tanggalKeluarSurat.split("-");
        String tahun = tanggal[2];

        this.nomorSurat =
                String.format("%02d", kategoriSurat)
                + "/"
                + String.format("%03d", urutanSuratBerikutnya)
                + "/UKMAsramaPutra/"
                + tahun;

        urutanSuratBerikutnya++;
    }

    // Constructor untuk membaca data dari database
    public SuratKeluar(
            int urutanSurat,
            String nomorSurat,
            String perihal,
            int kategoriSurat,
            String tanggalKeluarSurat,
            String penerima) {

        super(urutanSurat, nomorSurat, perihal);

        setKategoriSurat(kategoriSurat);
        setTanggalKeluarSurat(tanggalKeluarSurat);
        setPenerima(penerima);
    }

    public String getKategoriSurat() {

        if (kategoriSurat == 1) {
            return "SK";
        } else if (kategoriSurat == 2) {
            return "SU";
        } else if (kategoriSurat == 3) {
            return "SPm";
        } else if (kategoriSurat == 4) {
            return "SPb";
        } else if (kategoriSurat == 5) {
            return "SPp";
        } else if (kategoriSurat == 6) {
            return "SP";
        } else if (kategoriSurat == 7) {
            return "SM";
        } else if (kategoriSurat == 8) {
            return "ST";
        } else if (kategoriSurat == 9) {
            return "SKet";
        } else if (kategoriSurat == 10) {
            return "SR";
        } else if (kategoriSurat == 11) {
            return "SB";
        } else if (kategoriSurat == 12) {
            return "SRT";
        } else if (kategoriSurat == 13) {
            return "SPg";
        }

        return "Input tidak valid";
    }

    public int getKategoriSuratAngka() {
        return kategoriSurat;
    }

    public String getTanggalKeluarSurat() {
        return tanggalKeluarSurat;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setKategoriSurat(int kategoriSurat) {

        if (kategoriSurat >= MIN_KATEGORI
                && kategoriSurat <= MAX_KATEGORI) {

            this.kategoriSurat = kategoriSurat;

        } else {
            System.out.println(
                    "Kategori surat harus antara 1 sampai 13."
            );
        }
    }

    public void setTanggalKeluarSurat(
            String tanggalKeluarSurat) {

        if (tanggalKeluarSurat != null
                && !tanggalKeluarSurat.trim().isEmpty()) {

            this.tanggalKeluarSurat =
                    tanggalKeluarSurat.trim();

        } else {
            System.out.println(
                    "Tanggal keluar tidak boleh kosong."
            );
        }
    }

    public void setPenerima(String penerima) {

        if (penerima != null
                && !penerima.trim().isEmpty()) {

            this.penerima = penerima.trim();

        } else {
            System.out.println(
                    "Penerima tidak boleh kosong."
            );
        }
    }

    @Override
    public void tampilkanDaftarSurat() {

        System.out.println("==============================");
        System.out.println("DAFTAR SURAT KELUAR");
        tampilkanInfoDasar();
        System.out.println("Kategori      : " + getKategoriSurat());
        System.out.println("Tanggal Keluar: " + tanggalKeluarSurat);
        System.out.println("Penerima      : " + penerima);
        System.out.println("==============================");
    }
}
