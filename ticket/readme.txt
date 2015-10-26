1 ticket.ear 基本在JBOSS4.2.3部署通过。
2 由于ear打包问题，存在web容器和ejb容器类加载的问题，相互引用的类，特别是对ejb的本地调用，所以这些类不能保存双份，要把war包中删除，在ejb容器的类加载器加载，然后web容器进行引用即可，
这样能保证相互引用的类只存在ejb的类加载器中。具体删除掉了ticket.war\WEB-INF\classes\com\wrox\expertj2ee\ticket\boxoffice中的类，只保留ejb包中类。
3 为适应jboss4,应修改JBoss30OracleJdbcBoxOfficeDao类中的getOracleConnection方法，修改为
org.jboss.resource.adapter.jdbc.jdk5.WrappedConnectionJDK5 cp = (WrappedConnectionJDK5) con;
WrappedConnectionJDK5为jboss4对链接的包装类。