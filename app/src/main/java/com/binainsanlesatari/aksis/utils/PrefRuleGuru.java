package com.binainsanlesatari.aksis.utils;

import com.binainsanlesatari.aksis.model.GuruModel.Login.LoginGuru.Guru;

public interface PrefRuleGuru {

    void saveUser(Guru guru);

    Guru getUser();
}
