class Report
  attr_reader  :title, :text
  attr_accessor  :formatter

  def initialize(formatter)
    @title="Monthly Report"
    @text= ["Things are going","really, really well."]
    @formatter= formatter
  end

  def output_report()
    @formatter.output_report(self)#self is a reserved word
  end
end

class HTMLFormatter
    def output_report(context)#the actual parameter of content will be "self", which leads to initialized instance of Report that contains all instance variables
      puts("<html>")
      puts("    <head>")
      puts("      <title>#{context.title}</title>")
      puts("    </head>")
      puts("    <body>")

      context.text.each do |line|
        puts("    <p>#{line}</p>")
      end

      puts("    </body>")
      puts("    </html>")
    end
    end

class PlainTextFormatter
  def output_report(context)
    puts("***** #{context.title} *****")
    context.text.each do |line|
      puts(line)
    end
  end
end

report =Report.new(HTMLFormatter.new)
report.output_report
puts("\nanother output: ")
puts
report.formatter =PlainTextFormatter.new
report.output_report
