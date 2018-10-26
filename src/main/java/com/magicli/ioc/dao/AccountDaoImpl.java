package com.magicli.ioc.dao;

import com.magicli.ioc.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaonl on 2018/10/26.
 */
@Repository("accountDao")
@Qualifier("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired(required = true)
    @Qualifier("jdbcTemplate")
    private NamedParameterJdbcTemplate template;

    @Override
    public void update(Account account) {
        String sql = "update t_account set money = :money where id=:id";
        Map<String, Object> param = new HashMap<>();

        param.put("money", account.getMoney());
        param.put("id", account.getId());

        template.update(sql, param);

    }

    @Override
    public Account get(Integer accountId) {
        String sql = "select id,money,user_id from t_account where id=:id";
        Map<String, Object> param = new HashMap<>();

        param.put("id", accountId);

        return template.queryForObject(sql, param, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();

                account.setId(rs.getInt("id"));
                account.setMoney(rs.getInt("money"));
                account.setUserId(rs.getInt("user_id"));

                return account;
            }
        });
    }
}
