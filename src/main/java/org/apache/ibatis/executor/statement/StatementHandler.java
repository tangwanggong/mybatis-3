/**
 *    Copyright 2009-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

/**
 * 封装了对JDBC Statement对象的操作,比如为Statement对象设置参数,调用Statement接口提供的方法与数据库交互
 * @author Clinton Begin
 */
public interface StatementHandler {

  // 创建JDBC Statement对象,并完成Statement对象的属性设置
  Statement prepare(Connection connection, Integer transactionTimeout)
      throws SQLException;

  // 使用MyBatis中的ParameterHandler组件为PreparedStatement和CallableStatement参数占位符设置值
  void parameterize(Statement statement)
      throws SQLException;

  // 将SQL命令添加到批处理执行列表中
  void batch(Statement statement)
      throws SQLException;

  // Statement对象的execute()方法执行更新语句,如update,insert,delete,
  int update(Statement statement)
      throws SQLException;

  // 执行查询语句,并使用ResultHandler处理查询结果集
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  <E> Cursor<E> queryCursor(Statement statement)
      throws SQLException;

  // 获取Mapper中配置的SQL信息,BoundSql封装了SQL解析后的SQL文本和参数映射信息
  BoundSql getBoundSql();

  // 获取ParameterHandler实例
  ParameterHandler getParameterHandler();

}
