/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.PenghuniDAO;
import dao.SuratKeluarDAO;
import dao.SuratMasukDAO;
import java.util.ArrayList;
import model.Penghuni;
import model.SuratKeluar;
import model.SuratMasuk;
/**
 *
 * @author LENOVO
 */
public class ArsipController {

    private final SuratMasukDAO suratMasukDAO;
    private final SuratKeluarDAO suratKeluarDAO;
    private final PenghuniDAO penghuniDAO;

    public ArsipController() {
        suratMasukDAO = new SuratMasukDAO();
        suratKeluarDAO = new SuratKeluarDAO();
        penghuniDAO = new PenghuniDAO();
    }

    public ArrayList<SuratMasuk> getDaftarSuratMasuk() {
        return new ArrayList<>(
                suratMasukDAO.ambilSemuaSuratMasuk()
        );
    }

    public void tambahSuratMasuk(
            String nomorSurat,
            String perihal,
            String tanggalMasuk,
            String pengirim) {

        SuratMasuk suratBaru = new SuratMasuk(
                nomorSurat,
                perihal,
                tanggalMasuk,
                pengirim
        );

        suratMasukDAO.tambahSuratMasuk(suratBaru);
    }

    public void editSuratMasuk(
            int index,
            String nomorSurat,
            String perihal,
            String tanggalMasuk,
            String pengirim) {

        ArrayList<SuratMasuk> daftar = getDaftarSuratMasuk();

        if (index < 0 || index >= daftar.size()) {
            System.out.println("Data surat masuk tidak ditemukan.");
            return;
        }

        SuratMasuk surat = daftar.get(index);

        if (!nomorSurat.isEmpty()) {
            surat.setNomorSurat(nomorSurat);
        }

        if (!perihal.isEmpty()) {
            surat.setPerihal(perihal);
        }

        if (!tanggalMasuk.isEmpty()) {
            surat.setTanggalMasukSurat(tanggalMasuk);
        }

        if (!pengirim.isEmpty()) {
            surat.setPengirim(pengirim);
        }

        suratMasukDAO.updateSuratMasuk(
                surat.getUrutanSurat(),
                surat
        );
    }

    public void hapusSuratMasuk(int index) {
        ArrayList<SuratMasuk> daftar = getDaftarSuratMasuk();

        if (index < 0 || index >= daftar.size()) {
            System.out.println("Data surat masuk tidak ditemukan.");
            return;
        }

        int urutanSurat = daftar.get(index).getUrutanSurat();

        suratMasukDAO.hapusSuratMasuk(urutanSurat);
    }

    public ArrayList<SuratKeluar> getDaftarSuratKeluar() {
        return new ArrayList<>(
                suratKeluarDAO.ambilSemuaSuratKeluar()
        );
    }

    public void tambahSuratKeluar(
            String perihal,
            int kategori,
            String tanggalKeluar,
            String penerima) {

        SuratKeluar suratBaru = new SuratKeluar(
                perihal,
                kategori,
                tanggalKeluar,
                penerima
        );

        suratKeluarDAO.tambahSuratKeluar(suratBaru);
    }

    public void editSuratKeluar(
            int index,
            String perihal,
            String tanggalKeluar,
            String penerima) {

        ArrayList<SuratKeluar> daftar = getDaftarSuratKeluar();

        if (index < 0 || index >= daftar.size()) {
            System.out.println("Data surat keluar tidak ditemukan.");
            return;
        }

        SuratKeluar surat = daftar.get(index);

        if (!perihal.isEmpty()) {
            surat.setPerihal(perihal);
        }

        if (!tanggalKeluar.isEmpty()) {
            surat.setTanggalKeluarSurat(tanggalKeluar);
        }

        if (!penerima.isEmpty()) {
            surat.setPenerima(penerima);
        }

        suratKeluarDAO.updateSuratKeluar(
                surat.getUrutanSurat(),
                surat
        );
    }

    public void hapusSuratKeluar(int index) {
        ArrayList<SuratKeluar> daftar = getDaftarSuratKeluar();

        if (index < 0 || index >= daftar.size()) {
            System.out.println("Data surat keluar tidak ditemukan.");
            return;
        }

        int urutanSurat = daftar.get(index).getUrutanSurat();

        suratKeluarDAO.hapusSuratKeluar(urutanSurat);
    }

    public void cariSurat(String pihak) {
        boolean ditemukan = false;

        for (SuratMasuk surat : getDaftarSuratMasuk()) {
            if (surat.getPengirim() != null
                    && surat.getPengirim()
                            .toLowerCase()
                            .contains(pihak.toLowerCase())) {

                surat.tampilkanDaftarSurat();
                ditemukan = true;
            }
        }

        for (SuratKeluar surat : getDaftarSuratKeluar()) {
            if (surat.getPenerima() != null
                    && surat.getPenerima()
                            .toLowerCase()
                            .contains(pihak.toLowerCase())) {

                surat.tampilkanDaftarSurat();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Surat dengan pihak tersebut tidak ditemukan."
            );
        }
    }

    public void cariSurat(int kategori) {
        boolean ditemukan = false;

        for (SuratKeluar surat : getDaftarSuratKeluar()) {
            if (surat.getKategoriSuratAngka() == kategori) {
                surat.tampilkanDaftarSurat();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Surat keluar dengan kategori tersebut tidak ditemukan."
            );
        }
    }

    public ArrayList<Penghuni> getDaftarPenghuni() {
        return new ArrayList<>(
                penghuniDAO.ambilSemuaPenghuni()
        );
    }

    public void tambahPenghuni(Penghuni penghuni) {
        penghuniDAO.tambahPenghuni(penghuni);
    }

    public void editPenghuni(String nim, Penghuni penghuni) {
        penghuniDAO.updatePenghuni(nim, penghuni);
    }

    public void hapusPenghuni(String nim) {
        penghuniDAO.hapusPenghuni(nim);
    }

    public Penghuni cariPenghuni(String nim) {
        return penghuniDAO.cariPenghuniBerdasarkanNIM(nim);
    }
}
