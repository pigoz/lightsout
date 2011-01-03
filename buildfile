scala_v = '2.8.1'
Buildr.settings.build['scala.version'] = scala_v
require 'buildr/scala'

repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://scala-tools.org/repo-snapshots'

define 'lightsout' do
  project.version = '0.1.0'
  project.group = 'com.12dimensions'
  test.using :scalatest
  
  compile.with transitive("org.scala-lang:scala-swing:jar:#{scala_v}")
  package :jar
  
  task :gui => :compile do
    system 'scala -cp target/classes lightsout.views.GameWindow'
  end
end

Project.local_task :gui