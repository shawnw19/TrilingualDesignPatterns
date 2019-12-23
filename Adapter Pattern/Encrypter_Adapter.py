# a tricky usage of overloading built-in method read()

class Encrypter:
    def __init__(self, key):
        self.key = key

    def encrypt(self, reader, writer):
        self.reader = reader  # necessary else variable reader is not defined
        self.writer = writer  # idem
        key_index = 0
        while (True):
            self.clear_char = reader.read(1)
            if not self.clear_char: break
            # self.encrypted_char= chr((ord(self.clear_char))^ord('0'))
            self.encrypted_char = chr(ord(self.clear_char) ^ ord(self.key[key_index]))
            self.writer.write(self.encrypted_char)
            key_index = (key_index + 1) % len(self.key);


class StringIOAdapter:
    def __init__(self, string):
        self.string = string
        self.position = 0

    def read(self, digit):

        if self.position >= len(self.string):
            self.ch = None
        else:
            self.ch = self.string[self.position]
            self.position += 1

        return self.ch


## driver
reader = open('message.txt')
writer = open('message_encrypted.txt', 'w')
encrypter = Encrypter('my secret key')
encrypter.encrypt(reader, writer)
reader.close()
writer.close()

# decrypt it back
reader2 = open('message_encrypted.txt')
writer2 = open("message_decrypted.txt", 'w')
encrypter.encrypt(reader2, writer2)
reader2.close()
writer2.close()

# using adapter
reader3 = StringIOAdapter("this is a string!")
writer3 = open("message_adapted_encrypted.txt", 'w')
encrypter.encrypt(reader3, writer3)
writer3.close()

reader4 = open('message_adapted_encrypted.txt')
writer4 = open("message_adapted_decrypted.txt", 'w')
encrypter.encrypt(reader4, writer4)
reader4.close()
writer4.close()
