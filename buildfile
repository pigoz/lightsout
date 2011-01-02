Buildr.settings.build['scala.version'] = '2.8.1'
require 'buildr/scala'

repositories.remote << 'http://repo1.maven.org/maven2'
repositories.remote << 'http://scala-tools.org/repo-snapshots'

define 'lightsout' do
  project.version = '0.1.0'
  project.group = 'com.12dimensions'
  test.using :scalatest
  package :jar
end