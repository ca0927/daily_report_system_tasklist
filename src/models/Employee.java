package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
            name = "getAllEmployees",             // 全従業員情報を取得 (getAll~)
            query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
            ),
    @NamedQuery(
            name = "getEmployeesCount",         // 全従業員数を取得 (get~Count)
            query = "SELECT COUNT(e) FROM Employee AS e"
            ),
    @NamedQuery(
            name = "checkRegisteredCode",       //  指定した社員番号がすでにデータベースに存在しているか調べる (checkRegisteredCode)
            query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"
            ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",     // ログインする時に社員番号とパスワードが正しいかチェック (checkLoginCodeAndPassword)
            query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass"
            )
})
@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)     // 一意制約 (unique = ture：先にある社員番号は使用不可)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", length = 64, nullable = false)   // 入力文字数 (ハッシュ関数を利用)
    private String password;

    @Column(name = "admin_flag", nullable = false)
    private String admin_flag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "update_at", nullable = false)
    private Timestamp update_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmin_flag() {
        return admin_flag;
    }

    public void setAdmin_flag(String admin_flag) {
        this.admin_flag = admin_flag;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

}
