package apa.accessmodule.data.repository;

import apa.accessmodule.data.entity.AccountEntity;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginDataSource {
    AccountEntity login(String email, String password);
}
