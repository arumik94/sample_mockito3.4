package jp.co.sample;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class MockedClassTest {

    @Test
    public void test() throws Exception {

        // 期待値
        String expected_methodA = "test";
        String expected_methodB = "val2";

        // 対象クラスのモック化
        MockedStatic<MockedClass> mocked = Mockito.mockStatic(MockedClass.class);
        // 戻り値を設定してスタブ化
        mocked.when(MockedClass::methodA).thenReturn(expected_methodA);
        // スタブ化しない場合
        mocked.when(MockedClass::methodB).thenCallRealMethod();

        // 実行
        String actual_methodA = MockedClass.methodA();
        String actual_methodB = MockedClass.methodB();

        // 結果確認
        assertEquals(expected_methodA, actual_methodA);
        assertEquals(expected_methodB, actual_methodB);
        
        // その他
        // ラムダ式によるメソッド指定の方法
        mocked.when(() -> MockedClass.methodC(Mockito.anyString()))
        .thenReturn("stub");
        
        mocked.when(() -> MockedClass.methodA())
        .thenReturn("stub");
    }
}
