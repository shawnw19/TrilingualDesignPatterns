class Account
  attr_accessor :name, :balance

  def initialize(name, balance)
    @name= name
    @balance =balance
  end

  def <=>(other)
    balance <=>other.balance
  end
end

class Portfolio
  include Enumerable

  def initialize
    @accounts=[]
  end

  ##amperstand means to take a block as a parameter
  def each(&block)
    @accounts.each(&block)
  end

  def add_account(account)
    @accounts <<account
  end

end

act1=Account.new("Joe",300)
act2=Account.new("May",350)
my_portforlio=Portfolio.new
my_portforlio.add_account(act1)
my_portforlio.add_account(act2)

my_portforlio.each{|act| puts("Account owner: #{act.name}, balance: #{act.balance}")}
