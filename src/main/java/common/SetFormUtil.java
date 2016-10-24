package common;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class SetFormUtil {

    /** form. */
    private Object form;

    /** setしたいもの. */
    private LinkedHashSet<String> includes;

    /** set対象外にしたいもの. */
    private LinkedHashSet<String> excludes;

    /**
     * 他クラスに生成させない.
     */
    private SetFormUtil() {
    }

    /** formをセット. */
    public static SetFormUtil set(Object form) {
        SetFormUtil s = new SetFormUtil();
        s.form = form;
        return s;
    }

    /**
     * 画面に値を入力したいformのname属性値を設定.
     * 
     * @param includes
     *            name属性値
     * @return このクラス
     */
    public SetFormUtil includes(String... includes) {
        if (this.includes == null) {
            this.includes = new LinkedHashSet<String>();
        }
        for (String i : includes) {
            this.includes.add(i);
        }
        return this;
    }

    /**
     * include設定を除去
     * 
     * @return
     */
    public SetFormUtil removeIncludes() {
        includes = null;
        return this;
    }

    /**
     * 画面に値を入力したくないformのname属性値を設定.
     * 
     * @param excludes
     *            設定対象外のname属性値
     * @return このクラス
     */
    public SetFormUtil excludes(String... excludes) {
        if (this.excludes == null) {
            this.excludes = new LinkedHashSet<String>();
        }
        for (String e : excludes) {
            this.excludes.add(e);
        }
        return this;
    }

    /**
     * exclude設定を除去
     * 
     * @return
     */
    public SetFormUtil removeExcludes() {
        excludes = null;
        return this;
    }

    /**
     * 画面に値を入力する（実行！）。
     * 
     * @param driver
     * @param wait
     */
    public void exec(WebDriver driver, Wait<WebDriver> wait) {

        for (Field field : form.getClass().getDeclaredFields()) {
            String name = field.getName();

            // includesが設定されている場合、それで指定されたフィールドのみの値を画面に設定する。
            // includesが未設定の場合は、全フィールドの値の設定を試みる。
            if (includes != null && !includes.contains(name)) {
                continue;
            }

            // ただし、excludesが設定されていた場合、このフィールドは無視する。
            if (excludes != null && excludes.contains(name)) {
                continue;
            }

            try {
                Object _field = field.get(form);
                if (_field == null) {
                    continue;
                }
                // TODO TUtil依存除去したい。
                TUtil.setValByName(name, _field.toString(), driver, wait);
            } catch (IllegalArgumentException e) {
                e.getMessage();
            } catch (IllegalAccessException e) {
                e.getMessage();
            } catch (NoSuchElementException e) {
                // 画面に対応する要素がない場合でもエラーとはしない。
                e.getMessage();
            }
        }
    }
}
