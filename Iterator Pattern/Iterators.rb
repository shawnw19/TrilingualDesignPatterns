def for_each_element(array)
  i=0
  while i<array.length
    yield(array[i])
    i +=1
  end
end

a=[10,20,30,"a Husky dog","sandwich residue"]
for_each_element(a){|element| puts("The element is #{element}")}

puts
puts "Using native each method"
a.each { |element| puts("The element is #{element}")}

puts "-------------"

a=['joe','sam','george']
puts a.all?{|e| e.length<4}
puts
puts a.any?{|e| e.length<4}

## a thread safe remove/delete method

def change_resistant_for_each_element(array)
  copy =Array.new(array)
  i=0
  while i<copy.length
    copy[i]=array[i]
    i +=1
  end
  copy
end

array =["red","green","blue","purple"]
copy= change_resistant_for_each_element(array)
copy.each do |color|
  puts(color)
  if color=='green'
    array.delete(color)
  end
end

puts
puts"after deletion"
array.each { |color| puts(color)}