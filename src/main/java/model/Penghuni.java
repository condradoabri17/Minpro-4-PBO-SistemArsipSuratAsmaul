package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class Penghuni {

    private String nama;
    private String nim;
    private String asalDaerah;
    private String fakultas;
    private String jurusan;
    private String status;
    private String keterangan;

    public Penghuni(
            String nama,
            String nim,
            String asalDaerah,
            String fakultas,
            String jurusan,
            String status,
            String keterangan) {

        setNama(nama);
        setNim(nim);
        setAsalDaerah(asalDaerah);
        setFakultas(fakultas);
        setJurusan(jurusan);
        setStatus(status);
        setKeterangan(keterangan);
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getAsalDaerah() {
        return asalDaerah;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getStatus() {
        return status;
    }
    
    public String getKeterangan() {
        return keterangan;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama.trim();
        } else {
            System.out.println("Nama tidak boleh kosong.");
        }
    }

    public void setNim(String nim) {
        if (nim != null && !nim.trim().isEmpty()) {
            this.nim = nim.trim();
        } else {
            System.out.println("NIM tidak boleh kosong.");
        }
    }

    public void setAsalDaerah(String asalDaerah) {
        if (asalDaerah != null
                && !asalDaerah.trim().isEmpty()) {

            this.asalDaerah = asalDaerah.trim();

        } else {
            System.out.println("Asal daerah tidak boleh kosong.");
        }
    }

    public void setFakultas(String fakultas) {
        if (fakultas != null
                && !fakultas.trim().isEmpty()) {

            this.fakultas = fakultas.trim();

        } else {
            System.out.println("Fakultas tidak boleh kosong.");
        }
    }

    public void setJurusan(String jurusan) {
        if (jurusan != null
                && !jurusan.trim().isEmpty()) {

            this.jurusan = jurusan.trim();

        } else {
            System.out.println("Jurusan tidak boleh kosong.");
        }
    }

    public void setStatus(String status) {
        if (status != null
                && !status.trim().isEmpty()) {

            this.status = status.trim();

        } else {
            System.out.println("Status tidak boleh kosong.");
        }
    }
    
    public void setKeterangan(String keterangan) {
        if (keterangan == null || keterangan.trim().isEmpty()) {
            this.keterangan = null;
        } else {
            this.keterangan = keterangan.trim();
        }
    }
}
