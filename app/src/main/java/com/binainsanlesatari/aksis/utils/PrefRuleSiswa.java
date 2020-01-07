package com.binainsanlesatari.aksis.utils;

import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginSiswa.Siswa;

public interface PrefRuleSiswa {
    void saveSiswa(Siswa siswa);

    Siswa getSiswa();
}
