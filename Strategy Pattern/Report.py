class Report:

    def __init__(self,formatter):
        self.title='Monthly Report'
        self.text= ['Things are going','really, really well.']
        self.formatter=formatter

    def output_report(self):
        self.formatter.output_report(self)

class HTMLFormatter():
    def output_report(context):
        print('<html>\n')
        print('    <head>\n')
        print('      <title>', context.title, "</title>")
        print('    </head>\n')
        print('    <body>\n')

        for line in range(len(context.text)):
            print('    <p>', context.text[line], '</p>\n')
        print('    </body>\n')
        print('    </html>\n')

class PlainTextFormatter:
    def output_report(context):
        print("***** ",context.title," *****\n")
        for line in context.text:
            print(line,"\n")

report = Report(HTMLFormatter)
report.output_report()
print("\nanother output: \n")
report.formatter=PlainTextFormatter
report.output_report()